package com.sasd13.proadmin.android.component.report.view;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.report.scope.ReportScope;
import com.sasd13.proadmin.android.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.util.sorter.RunningTeamSorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportNewFragmentRunningTeams extends Fragment implements Observer {

    private static class ActionSelectRunningTeam implements IAction {

        private RunningTeamItemModel model;
        private List<RunningTeamItemModel> models;
        private RunningTeam runningTeam;
        private Report report;
        private ReportNewFragment parentFragment;

        private ActionSelectRunningTeam(RunningTeamItemModel model, List<RunningTeamItemModel> models, RunningTeam runningTeam, Report report, ReportNewFragment parentFragment) {
            this.model = model;
            this.models = models;
            this.runningTeam = runningTeam;
            this.report = report;
            this.parentFragment = parentFragment;
        }

        @Override
        public void execute() {
            if (model.isSelected()) {
                model.setSelected(false);
                report.setRunningTeam(null);
            } else {
                for (RunningTeamItemModel model : models) {
                    model.setSelected(false);
                }

                model.setSelected(true);
                report.setRunningTeam(runningTeam);
                parentFragment.forward();
            }
        }
    }

    private IReportController controller;
    private ReportScope scope;
    private ReportNewFragment parentFragment;
    private Recycler recycler;

    public static ReportNewFragmentRunningTeams newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentRunningTeams fragment = new ReportNewFragmentRunningTeams();

        fragment.parentFragment = parentFragment;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabRunningTeams(view);
        buildFloatingActionButton(view);
        bindTabWithRunningTeams(scope.getRunningTeams());
    }

    private void buildTabRunningTeams(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_navigate_next_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward();
            }
        });
    }

    private void forward() {
        if (scope.getReport().getRunningTeam() != null) {
            parentFragment.forward();
        } else {
            controller.display(getString(R.string.error_no_runningteam_selected));
        }
    }

    private void bindTabWithRunningTeams(List<RunningTeam> runningTeams) {
        recycler.clear();
        RunningTeamSorter.byRunningYearAndProjectCode(runningTeams);
        addRunningTeamsToTab(runningTeams);
    }

    private void addRunningTeamsToTab(List<RunningTeam> runningTeams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;
        RunningTeamItemModel model;
        List<RunningTeamItemModel> models = new ArrayList<>();

        for (RunningTeam runningTeam : runningTeams) {
            model = new RunningTeamItemModel(runningTeam);
            pair = new RecyclerHolderPair(model);

            if (runningTeam.equals(scope.getReport().getRunningTeam())) {
                model.setSelected(true);
            }

            models.add(model);
            pair.addController(EnumActionEvent.CLICK, new ActionSelectRunningTeam(model, models, runningTeam, scope.getReport(), parentFragment));
            holder.add(String.valueOf(runningTeam.getRunning().getYear()), pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindTabWithRunningTeams(scope.getRunningTeams());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}