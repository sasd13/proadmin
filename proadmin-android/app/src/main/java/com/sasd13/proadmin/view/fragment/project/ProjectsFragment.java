package com.sasd13.proadmin.view.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.scope.ProjectScope;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.view.IProjectController;
import com.sasd13.proadmin.view.gui.tab.ProjectItemModel;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProjectsFragment extends Fragment implements Observer {

    private IProjectController controller;
    private ProjectScope scope;
    private Recycler recycler;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IProjectController) ((MainActivity) getActivity()).lookup(IProjectController.class);
        scope = (ProjectScope) controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_srl, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabProjects(view);
        buildSwipeRefreshLayout(view);
        bindTabWithProjects(scope.getProjects());
    }

    private void buildTabProjects(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                controller.actionLoadProjects();
            }
        });
    }

    private void bindTabWithProjects(List<Project> projects) {
        ProjectsSorter.byDateCreation(projects);
        addProjectsToTab(projects);
    }

    private void addProjectsToTab(List<Project> projects) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Project project : projects) {
            pair = new RecyclerHolderPair(new ProjectItemModel(project));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.actionShowProject(project);
                }
            });

            holder.add(String.valueOf(new DateTime(project.getDateCreation()).getYear()), pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_projects));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        swipeRefreshLayout.setRefreshing(scope.isLoading());
        bindTabWithProjects(scope.getProjectsToAdd());
    }

    @Override
    public void onPause() {
        super.onPause();

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.destroyDrawingCache();
        swipeRefreshLayout.clearAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}