package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
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

    private ReportsActivity parentActivity;
    private ReportNewFragment parentFragment;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Recycler runningTeamsTab;

    private RunningTeam runningTeam;
    private List<RunningTeam> runningTeams;

    private RunningTeamsService service;

    public static ReportNewFragmentRunningTeams newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentRunningTeams fragment = new ReportNewFragmentRunningTeams();
        fragment.parentFragment = parentFragment;

        return fragment;
    }

    public static ReportNewFragmentRunningTeams newInstance(ReportNewFragment parentFragment, RunningTeam runningTeam) {
        ReportNewFragmentRunningTeams fragment = newInstance(parentFragment);
        fragment.runningTeam = runningTeam;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
        runningTeams = new ArrayList<>();
        service = new RunningTeamsService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_srl, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildSwipeRefreshLayout(view);
        buildTabRunningTeams(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readRunningTeamsFromWS();
            }
        });
    }

    private void buildTabRunningTeams(View view) {
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_report_new, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.setGroupVisible(R.id.menu_report_new_group_previous, false);
        menu.setGroupVisible(R.id.menu_report_new_group_save, false);
        menu.setGroupVisible(R.id.menu_report_new_group_next, true);
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

            if (this.runningTeam != null && Comparator.areTheSame(this.runningTeam, runningTeam)) {
                model.setSelected(true);
            }

            pair.addController(EnumActionEvent.CLICK, new ActionSelectRunningTeam(runningTeam, model, parentFragment));
            holder.add(String.valueOf(runningTeam.getRunning().getYear()), pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void onError(List<String> errors) {
        swipeRefreshLayout.setRefreshing(false);
        displayError(WebServiceUtils.handleErrors(getContext(), errors));
    }

    public void displayError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}