package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.util.Comparator;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.util.wrapper.ReportNewWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportNewFragmentRunningTeams extends Fragment implements Observer {

    private static class ActionSelectRunningTeam implements IAction {

        private RunningTeamItemModel model;
        private RunningTeam runningTeam;
        private ReportNewFragment parentFragment;
        private IReportController controller;

        private ActionSelectRunningTeam(RunningTeamItemModel model, RunningTeam runningTeam, ReportNewFragment parentFragment, IReportController controller) {
            this.model = model;
            this.runningTeam = runningTeam;
            this.parentFragment = parentFragment;
            this.controller = controller;
        }

        @Override
        public void execute() {
            if (model.isSelected()) {
                model.setSelected(false);
                parentFragment.getReportToCreate().setRunningTeam(null);
            } else {
                model.setSelected(true);
                parentFragment.getReportToCreate().setRunningTeam(runningTeam);
                controller.readStudentTeams(runningTeam.getTeam());
                parentFragment.forward();
            }
        }
    }

    private IReportController controller;
    private ReportNewFragment parentFragment;
    private Recycler runningTeamsTab;

    public static ReportNewFragmentRunningTeams newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentRunningTeams fragment = new ReportNewFragmentRunningTeams();
        fragment.parentFragment = parentFragment;
        parentFragment.getReportNewWrapper().addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
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
        buildTabRunningTeams(view);
        buildFloatingActionButton(view);
        bindRunningTeams(parentFragment.getReportNewWrapper().getRunningTeams());
    }

    private void buildTabRunningTeams(View view) {
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_navigate_next_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goForward();
            }
        });
    }

    private void goForward() {
        parentFragment.forward();
    }

    private void bindRunningTeams(List<RunningTeam> runningTeams) {
        RunningTeamsSorter.byRunningYear(runningTeams);
        addRunningTeamsToTab(runningTeams);
    }

    private void addRunningTeamsToTab(List<RunningTeam> runningTeams) {
        runningTeamsTab.clear();

        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;
        RunningTeamItemModel model;

        for (RunningTeam runningTeam : runningTeams) {
            model = new RunningTeamItemModel(runningTeam);
            pair = new RecyclerHolderPair(model);

            if (parentFragment.getReportToCreate().getRunningTeam() != null && Comparator.areTheSame(parentFragment.getReportToCreate().getRunningTeam(), runningTeam)) {
                model.setSelected(true);
            }

            pair.addController(EnumActionEvent.CLICK, new ActionSelectRunningTeam(model, runningTeam, parentFragment, controller));
            holder.add(String.valueOf(runningTeam.getRunning().getYear()), pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        ReportNewWrapper reportNewWrapper = (ReportNewWrapper) observable;

        bindRunningTeams(reportNewWrapper.getRunningTeams());
    }
}