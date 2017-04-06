package com.sasd13.proadmin.view.fragment.project;

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
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.scope.ProjectScope;
import com.sasd13.proadmin.view.gui.tab.RunningItemModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProjectDetailsFragmentRunnings extends Fragment implements Observer {

    private IRunningController controller;
    private ProjectScope scope;
    private Recycler recycler;

    public static ProjectDetailsFragmentRunnings newInstance() {
        return new ProjectDetailsFragmentRunnings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IRunningController) ((MainActivity) getActivity()).lookup(IRunningController.class);
        scope = (ProjectScope) controller.getScope();

        scope.addObserver(this);
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
                controller.newRunning(scope.getProject());
            }
        });
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
        scope = (ProjectScope) observable;

        bindTabWithRunnings(scope.getRunnings());

        /*if (!runnings.containsAll(projectWrapper.getRunnings())) {
            addNextRunnings(projectWrapper.getRunnings());
        }*/
    }

    private void bindTabWithRunnings(List<Running> runnings) {
        addRunningsToTab(runnings);
    }

    /*private void addNextRunnings(List<Running> nextRunnings) {
        runnings.addAll(nextRunnings);
        bindTabWithRunnings(nextRunnings);
    }*/
}