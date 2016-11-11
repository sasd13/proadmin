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
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.project.ProjectsYearsBuilder;
import com.sasd13.proadmin.util.filter.project.ProjectDateCreationCriteria;
import com.sasd13.proadmin.util.sorter.IntegersSorter;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.wrapper.read.project.IProjectReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectsFragment extends Fragment implements IReadServiceCaller<IProjectReadWrapper> {

    private ProjectsActivity parentActivity;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spin spinYears;
    private Recycler projectsTab;

    private List<Integer> years;
    private List<Project> projects;

    private ProjectReadService projectReadService;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ProjectsActivity) getActivity();
        years = new ArrayList<>();
        projects = new ArrayList<>();
        projectReadService = new ProjectReadService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_srl, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabProjects(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readProjectsFromWS();
            }
        });
    }

    private void readProjectsFromWS() {
        projectReadService.readProjects();
    }

    private void buildTabProjects(View view) {
        projectsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_recyclerview));
        projectsTab.addDividerItemDecoration();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_projects));
        readProjectsFromWS();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_projects, menu);

        buildSpinYears(menu.findItem(R.id.menu_projects_spinner));
    }

    private void buildSpinYears(MenuItem menuItem) {
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

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(IProjectReadWrapper projectReadWrapper) {
        swipeRefreshLayout.setRefreshing(false);
        bindYears(projectReadWrapper.getProjects());
        bindProjects(projectReadWrapper.getProjects());
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

    @Override
    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}