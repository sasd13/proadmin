package com.sasd13.proadmin.view.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
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
import com.sasd13.javaex.util.sorter.IntegersSorter;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.project.ProjectsYearsBuilder;
import com.sasd13.proadmin.util.filter.project.ProjectDateCreationCriteria;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.util.wrapper.ProjectsWrapper;
import com.sasd13.proadmin.view.IProjectController;

import java.util.List;

public class ProjectsFragment extends Fragment {

    private IProjectController controller;
    private List<Project> projects;
    private List<Integer> years;
    private Spin spinYears;
    private Recycler projectsTab;

    public static ProjectsFragment newInstance(IProjectController controller, ProjectsWrapper projectsWrapper) {
        ProjectsFragment fragment = new ProjectsFragment();
        fragment.controller = controller;
        fragment.projects = projectsWrapper.getProjects();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabProjects(view);
    }

    private void buildTabProjects(View view) {
        projectsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        projectsTab.addDividerItemDecoration();
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

        bindSpinWithProjectsYears();
        bindTabWithProjects();
    }

    private void bindSpinWithProjectsYears() {
        years = new ProjectsYearsBuilder(projects).build();

        IntegersSorter.byDesc(years);
        fillSpinYear(new IntegersToStringsAdapter().adapt(years));
    }

    private void fillSpinYear(List<String> years) {
        spinYears.clear();
        spinYears.addItems(years);
        spinYears.resetPosition();
    }

    private void bindTabWithProjects() {
        fillTabProjectsByYearCreated();
    }

    private void fillTabProjectsByYearCreated() {
        int year = years.get(spinYears.getSelectedPosition());
        List<Project> projectsToTab = new ProjectDateCreationCriteria(year).meetCriteria(projects);

        ProjectsSorter.byCode(projectsToTab);
        projectsTab.clear();
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
                    controller.showProject(project);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(projectsTab, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_projects));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}