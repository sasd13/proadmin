package com.sasd13.proadmin.activity.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.tab.RunningItemModel;
import com.sasd13.proadmin.service.running.RunningReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsFragmentRunnings extends Fragment implements IReadServiceCaller<IReadWrapper<Running>> {

    private ProjectsActivity parentActivity;

    private Recycler runningsTab;

    private Project project;
    private List<Running> runnings;

    private RunningReadService runningReadService;

    public static ProjectDetailsFragmentRunnings newInstance(Project project) {
        ProjectDetailsFragmentRunnings fragment = new ProjectDetailsFragmentRunnings();
        fragment.project = project;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ProjectsActivity) getActivity();
        runnings = new ArrayList<>();
        runningReadService = new RunningReadService(this);
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
        runningsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        runningsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newRunning(project);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        readRunningsFromWS();
    }

    private void readRunningsFromWS() {
        runningReadService.read(SessionHelper.getExtraIdTeacherNumber(getContext()), project);
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Running> runningReadWrapper) {
        runnings.clear();
        runnings.addAll(runningReadWrapper.getWrapped());
        fillTabRunningsByYear();
    }

    private void fillTabRunningsByYear() {
        runningsTab.clear();
        RunningsSorter.byYear(runnings);
        addRunningsToTab();
    }

    private void addRunningsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Running running : runnings) {
            pair = new RecyclerHolderPair(new RunningItemModel(running));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showRunning(running);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(runningsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}