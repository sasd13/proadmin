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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.controller.ProjectsActivity;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.project.ProjectsYearsBuilder;
import com.sasd13.proadmin.util.filter.project.ProjectDateCreationCriteria;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;

import java.util.ArrayList;
import java.util.List;

public class ProjectsFragment extends Fragment {

    private Spin spinYears;
    private Recycler projectsTab;

    private List<String> years;
    private List<Project> projects;

    public static ProjectsFragment newInstance(List<Project> projects) {
        ProjectsFragment fragment = new ProjectsFragment();
        fragment.projects = projects;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        years = new ArrayList<>();
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
        bindYearsWithSpin();
        bindProjectsWithTab();
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

    private void bindYearsWithSpin() {
        List<Integer> projectsYears = new ProjectsYearsBuilder(projects).build();

        IntegersSorter.byDesc(projectsYears);
        years.addAll(new IntegersToStringsAdapter().adapt(projectsYears));
        spinYears.addItems(years);
        spinYears.resetPosition();
    }

    private void bindProjectsWithTab() {
        fillTabProjectsByYearCreated();
    }

    private void fillTabProjectsByYearCreated() {
        projectsTab.clear();

        int year = Integer.parseInt(years.get(spinYears.getSelectedPosition()));
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
                    ((ProjectsActivity) getActivity()).showProject(project);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(projectsTab, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((ProjectsActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_projects));
        ((ProjectsActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}