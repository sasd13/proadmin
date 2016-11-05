package com.sasd13.proadmin.activities.fragments.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.pattern.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.pattern.builder.project.ProjectsYearsBuilder;
import com.sasd13.proadmin.service.ProjectsService;
import com.sasd13.proadmin.util.filter.project.ProjectDateCreationCriteria;
import com.sasd13.proadmin.util.sorter.IntegersSorter;
import com.sasd13.proadmin.util.sorter.ProjectsSorter;

import java.util.ArrayList;
import java.util.List;

public class ProjectsFragment extends Fragment {

    private ProjectsActivity parentActivity;
    private ProjectsService projectsService;
    private Recycler projectsTab;
    private List<Integer> years;
    private List<Project> projects;
    private Spin spinYears;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ProjectsActivity) getActivity();
        projectsService = new ProjectsService(this);
        years = new ArrayList<>();
        projects = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        buildView(view);
        bindView();

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabProjects(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.projects_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readProjectsFromWS();
            }
        });
    }

    private void readProjectsFromWS() {
        projectsService.readProjects();
    }

    private void buildTabProjects(View view) {
        projectsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.projects_recyclerview));
        projectsTab.addDividerItemDecoration();
    }

    private void bindView() {
        readProjectsFromWS();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_projects));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_projects, menu);

        buildSpinAcademicLevels(menu.findItem(R.id.menu_projects_spinner));
    }

    private void buildSpinAcademicLevels(MenuItem menuItem) {
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        spinYears = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabProjectsByYearCreated();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(List<Project> projectsFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        bindYears(projectsFromWS);
        bindProjects(projectsFromWS);
    }

    private void bindYears(List<Project> projectsFromWS) {
        List<Integer> yearsToSpin = new ProjectsYearsBuilder(projectsFromWS).build();

        IntegersSorter.byDesc(yearsToSpin);
        years.clear();
        years.addAll(yearsToSpin);

        fillSpinYears();
    }

    private void fillSpinYears() {
        spinYears.clear();
        spinYears.addItems(new IntegersToStringsAdapter().adapt(years));
        spinYears.resetPosition();
    }

    private void bindProjects(List<Project> projectsFromWS) {
        projects.clear();
        projects.addAll(projectsFromWS);

        fillTabProjectsByYearCreated();
    }

    private void fillTabProjectsByYearCreated() {
        projectsTab.clear();

        int year = years.get(spinYears.getSelectedPosition());
        List<Project> projectsToTab = new ProjectDateCreationCriteria(year).meetCriteria(projects);

        ProjectsSorter.byCode(projectsToTab);
        addProjectsToTab(projectsToTab);
    }

    private void addProjectsToTab(List<Project> projects) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Project project : projects) {
            pair = new RecyclerHolderPair(new ProjectItemModel(project));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showProject(project);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(projectsTab, holder);
    }

    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}