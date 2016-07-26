package com.sasd13.proadmin.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.form.ProjectForm;
import com.sasd13.proadmin.handler.ProjectHandler;
import com.sasd13.proadmin.util.Binder;

public class ProjectFragment extends Fragment {

    private Project project;
    private ProjectsActivity parentActivity;
    private ProjectHandler projectHandler;
    private ProjectForm formProject;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ProjectFragment newInstance(Project project) {
        ProjectFragment projectFragment = new ProjectFragment();
        projectFragment.project = project;

        return projectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ProjectsActivity) getActivity();
        projectHandler = new ProjectHandler(this);
        formProject = new ProjectForm(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.project_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                projectHandler.readProjects(project.getId());
            }
        });

        buildFormProject(view);
    }

    private void buildFormProject(View view) {
        Recycler form = RecyclerFactory
                .makeBuilder(EnumFormType.FORM)
                .build((RecyclerView) view.findViewById(R.id.projects_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, formProject.getHolder());

        refreshView();
    }

    private void refreshView() {
        formProject.bind(project);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_project, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_project_action_runnings:
                listRunnings();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void listRunnings() {
        parentActivity.listRunnings(project);
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(Project project) {
        swipeRefreshLayout.setRefreshing(false);
        Binder.bind(this.project, project);
        refreshView();
    }

    public void onError(String message) {
        swipeRefreshLayout.setRefreshing(false);

        Project project = projectHandler.readProjectFromCache(this.project.getId());
        Binder.bind(this.project, project);
        refreshView();
    }
}