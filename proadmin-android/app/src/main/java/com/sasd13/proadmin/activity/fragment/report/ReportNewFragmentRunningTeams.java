package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.javaex.util.sorter.IntegersSorter;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.service.running.RunningTeamReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.running.RunningTeamsYearsBuilder;
import com.sasd13.proadmin.util.filter.running.RunningTeamYearCriteria;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class ReportNewFragmentRunningTeams extends Fragment implements IReadServiceCaller<IReadWrapper<RunningTeam>> {

    private ReportsActivity parentActivity;
    private ReportNewFragment parentFragment;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spin spinYears;
    private Recycler runningTeamsTab;

    private RunningTeam runningTeam;
    private List<Integer> years;
    private List<RunningTeam> runningTeams;

    private RunningTeamReadService runningTeamReadService;

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
        years = new ArrayList<>();
        runningTeams = new ArrayList<>();
        runningTeamReadService = new RunningTeamReadService(this);
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
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_report_new, menu);

        buildSpinYears(menu.findItem(R.id.menu_report_new_spinner));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.setGroupVisible(R.id.menu_report_new_group_save, false);
        menu.setGroupVisible(R.id.menu_report_new_group_runingteams, true);
        menu.setGroupVisible(R.id.menu_report_new_group_next, true);
    }

    private void buildSpinYears(MenuItem menuItem) {
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        spinYears = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabRunningTeamsByYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setSubtitle(getResources().getString(R.string.title_runningteams));
        readRunningTeamsFromWS();
    }

    private void readRunningTeamsFromWS() {
        runningTeamReadService.read(SessionHelper.getExtraIdTeacherNumber(getContext()));
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(IReadWrapper<RunningTeam> runningTeamReadWrapper) {
        swipeRefreshLayout.setRefreshing(false);
        bindYears(runningTeamReadWrapper.getWrapped());
        bindRunningTeams(runningTeamReadWrapper.getWrapped());
    }

    private void bindYears(List<RunningTeam> runningTeamFromWS) {
        List<Integer> yearsToSpin = new RunningTeamsYearsBuilder(runningTeamFromWS).build();

        IntegersSorter.byDesc(yearsToSpin);
        years.clear();
        years.addAll(yearsToSpin);
        fillSpinYears();
    }

    private void fillSpinYears() {
        spinYears.clear();
        spinYears.addItems(new IntegersToStringsAdapter().adapt(years));
        spinYears.resetPosition();
    }

    private void bindRunningTeams(List<RunningTeam> runningTeamFromWS) {
        runningTeams.clear();
        runningTeams.addAll(runningTeamFromWS);
        fillTabRunningTeamsByYear();
    }

    private void fillTabRunningTeamsByYear() {
        runningTeamsTab.clear();

        int year = years.get(spinYears.getSelectedPosition());
        List<RunningTeam> runningTeamsToTab = new RunningTeamYearCriteria(year).meetCriteria(runningTeams);

        RunningTeamsSorter.byAcademicLevelCode(runningTeamsToTab);
        addRunningTeamsToTab(runningTeamsToTab);
    }

    private void addRunningTeamsToTab(List<RunningTeam> runningTeams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;
        RunningTeamItemModel model;

        for (RunningTeam runningTeam : runningTeams) {
            model = new RunningTeamItemModel(runningTeam);
            pair = new RecyclerHolderPair(model);

            if (runningTeam.equals(this.runningTeam)) {
                model.setSelected(true);
            }

            pair.addController(EnumActionEvent.CLICK, new ActionSelectRunningTeam(runningTeam, model, parentFragment));
            holder.add(runningTeam.getAcademicLevel().getCode(), pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}