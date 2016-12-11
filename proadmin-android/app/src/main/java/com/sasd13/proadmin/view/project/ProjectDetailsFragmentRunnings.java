package com.sasd13.proadmin.view.project;

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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.util.wrapper.ProjectWrapper;
import com.sasd13.proadmin.view.IProjectController;

import java.util.List;

public class ProjectDetailsFragmentRunnings extends Fragment {

    private IProjectController controller;
    private Project project;
    private List<Running> runnings;
    private Recycler runningsTab;

    public static ProjectDetailsFragmentRunnings newInstance(IProjectController controller, ProjectWrapper projectWrapper) {
        ProjectDetailsFragmentRunnings fragment = new ProjectDetailsFragmentRunnings();
        fragment.controller = controller;
        fragment.project = projectWrapper.getProject();
        fragment.runnings = projectWrapper.getRunnings();

        return fragment;
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
        bindTabWithRunnings();
    }

    private void buildTabRunnings(View view) {
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        runningsTab.addDividerItemDecoration();
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

    private void bindTabWithRunnings() {
        RunningsSorter.byYear(runnings);
        runningsTab.clear();
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

        RecyclerHelper.addAll(runningsTab, holder);
    }
}