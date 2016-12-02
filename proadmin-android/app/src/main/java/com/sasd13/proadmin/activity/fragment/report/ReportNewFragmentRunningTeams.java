package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.util.Comparator;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.ws.service.RunningTeamsService;

import java.util.ArrayList;
import java.util.List;

public class ReportNewFragmentRunningTeams extends Fragment implements RunningTeamsService.ReadCaller {

    private static class ActionSelectRunningTeam implements IAction {

        private RunningTeamItemModel model;
        private RunningTeam runningTeam;
        private ReportNewFragment parentFragment;

        private ActionSelectRunningTeam(RunningTeamItemModel model, RunningTeam runningTeam, ReportNewFragment parentFragment) {
            this.model = model;
            this.runningTeam = runningTeam;
            this.parentFragment = parentFragment;
        }

        @Override
        public void execute() {
            if (model.isSelected()) {
                model.setSelected(false);
                parentFragment.getReportToCreate().setRunningTeam(null);
            } else {
                model.setSelected(true);
                parentFragment.getReportToCreate().setRunningTeam(runningTeam);
                parentFragment.forward();
            }
        }
    }

    private ReportNewFragment parentFragment;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Recycler runningTeamsTab;

    private List<RunningTeam> runningTeams;

    private RunningTeamsService service;

    public static ReportNewFragmentRunningTeams newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentRunningTeams fragment = new ReportNewFragmentRunningTeams();
        fragment.parentFragment = parentFragment;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runningTeams = new ArrayList<>();
        service = new RunningTeamsService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_srl_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabRunningTeams(view);
        buildFloatingActionButton(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_fab_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readRunningTeamsFromWS();
            }
        });
    }

    private void buildTabRunningTeams(View view) {
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_srl_fab_floatingactionbutton);
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

    @Override
    public void onStart() {
        super.onStart();

        readRunningTeamsFromWS();
    }

    private void readRunningTeamsFromWS() {
        service.read(SessionHelper.getExtraIdTeacherNumber(getContext()));
    }

    @Override
    public void onWaiting() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReaded(List<RunningTeam> runningTeamsFromWS) {
        swipeRefreshLayout.setRefreshing(false);
        bindRunningTeams(runningTeamsFromWS);
    }

    private void bindRunningTeams(List<RunningTeam> runningTeamFromWS) {
        runningTeams.clear();
        runningTeams.addAll(runningTeamFromWS);
        fillTabRunningTeamsByYear();
    }

    private void fillTabRunningTeamsByYear() {
        runningTeamsTab.clear();
        RunningTeamsSorter.byRunningYear(runningTeams);
        addRunningTeamsToTab(runningTeams);
    }

    private void addRunningTeamsToTab(List<RunningTeam> runningTeams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;
        RunningTeamItemModel model;

        for (RunningTeam runningTeam : runningTeams) {
            model = new RunningTeamItemModel(runningTeam);
            pair = new RecyclerHolderPair(model);

            if (parentFragment.getReportToCreate().getRunningTeam() != null && Comparator.areTheSame(parentFragment.getReportToCreate().getRunningTeam(), runningTeam)) {
                model.setSelected(true);
            }

            pair.addController(EnumActionEvent.CLICK, new ActionSelectRunningTeam(model, runningTeam, parentFragment));
            holder.add(String.valueOf(runningTeam.getRunning().getYear()), pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void onErrors(List<String> errors) {
        swipeRefreshLayout.setRefreshing(false);
        displayMessage(WebServiceUtils.handleErrors(getContext(), errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}