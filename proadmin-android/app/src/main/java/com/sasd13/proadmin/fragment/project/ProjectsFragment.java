package com.sasd13.proadmin.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

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
import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.handler.ProjectsHandler;
import com.sasd13.proadmin.util.filter.project.AcademicLevelCriteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectsFragment extends Fragment {

    private ProjectsActivity parentActivity;
    private ProjectsHandler projectsHandler;
    private Recycler projectsTab;
    private List<Project> projects;
    private Spin spinAcademicLevels;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ProjectsActivity) getActivity();
        projectsHandler = new ProjectsHandler(this);
        projects = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildSwipeRefreshLayout(view);
        buildTabProjects(view);
        readProjects();
    }

    private void readProjects() {
        if (projects.isEmpty()) {
            readProjectsFromCache();
            refreshView();
        } else {
            refreshView();
            readProjectsFromWS();
        }
    }

    private void readProjectsFromCache() {
        projects.addAll(projectsHandler.readProjectsFromCache());
    }

    private void refreshView() {
        int position = spinAcademicLevels != null && spinAcademicLevels.getSelectedPosition() > -1
                ? spinAcademicLevels.getSelectedPosition()
                : 0;
        fillTabProjectsByAcademicLevel(EnumAcademicLevel.values()[position]);
    }

    private void readProjectsFromWS() {
        projectsHandler.readProjects();
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

    private void buildTabProjects(View view) {
        projectsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.projects_recyclerview));
        projectsTab.addDividerItemDecoration();
    }

    private void fillTabProjectsByAcademicLevel(EnumAcademicLevel academicLevel) {
        projectsTab.clear();

        addProjectsToTab(new AcademicLevelCriteria(academicLevel).meetCriteria(projects));
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

        spinAcademicLevels = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabProjectsByAcademicLevel(EnumAcademicLevel.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinAcademicLevels.addAll(Arrays.asList(getResources().getStringArray(R.array.academiclevels)));
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(List<Project> projectsFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        projects.clear();
        projects.addAll(projectsFromWS);

        refreshView();
    }

    public void onError(String message) {
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}