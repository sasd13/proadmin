package com.sasd13.proadmin.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.fragment.IRunningController;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.util.wrapper.ProjectWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProjectDetailsFragmentRunnings extends Fragment implements Observer {

    private IRunningController controller;
    private Project project;
    private List<Running> runnings;
    private Recycler recycler;

    public static ProjectDetailsFragmentRunnings newInstance(ProjectWrapper projectWrapper) {
        ProjectDetailsFragmentRunnings fragment = new ProjectDetailsFragmentRunnings();
        fragment.project = projectWrapper.getProject();
        fragment.runnings = projectWrapper.getRunnings();

        projectWrapper.addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabRunnings(view);
        buildFloatingActionButton(view);
        bindTabWithRunnings(runnings);
    }

    private void buildTabRunnings(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (project != null) {
                    controller.newRunning(project);
                }
            }
        });
    }

    private void bindTabWithRunnings(List<Running> runnings) {
        addRunningsToTab(runnings);
    }

    private void addRunningsToTab(List<Running> runnings) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Running running : runnings) {
            pair = new RecyclerHolderPair(new RunningItemModel(running));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.showRunning(running);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        ProjectWrapper projectWrapper = (ProjectWrapper) observable;

        if (!runnings.containsAll(projectWrapper.getRunnings())) {
            addNextRunnings(projectWrapper.getRunnings());
        }
    }

    private void addNextRunnings(List<Running> nextRunnings) {
        runnings.addAll(nextRunnings);
        bindTabWithRunnings(nextRunnings);
    }
}