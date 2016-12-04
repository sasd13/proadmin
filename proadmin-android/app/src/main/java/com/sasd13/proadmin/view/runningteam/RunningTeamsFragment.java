package com.sasd13.proadmin.view.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.javaex.util.sorter.IntegersSorter;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.RunningTeamsActivity;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.adapter.IntegersToStringsAdapter;
import com.sasd13.proadmin.util.builder.running.RunningTeamsYearsBuilder;
import com.sasd13.proadmin.util.filter.running.RunningTeamYearCriteria;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.ws.service.RunningTeamsService;

import java.util.ArrayList;
import java.util.List;

public class RunningTeamsFragment extends Fragment implements RunningTeamsService.ReadCaller {

    private RunningTeamsActivity parentActivity;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spin spinYears;
    private Recycler runningTeamsTab;

    private List<Integer> years;
    private List<RunningTeam> runningTeams;

    private RunningTeamsService service;

    public static RunningTeamsFragment newInstance() {
        return new RunningTeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningTeamsActivity) getActivity();
        years = new ArrayList<>();
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
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newRunningTeam();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_runningteams, menu);

        buildSpinYears(menu.findItem(R.id.menu_runningteams_spinner));
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

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteams));
        parentActivity.getSupportActionBar().setSubtitle(null);
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
        bindYears(runningTeamsFromWS);
        bindRunningTeams(runningTeamsFromWS);
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

        for (final RunningTeam runningTeam : runningTeams) {
            pair = new RecyclerHolderPair(new RunningTeamItemModel(runningTeam));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showRunningTeam(runningTeam);
                }
            });

            holder.add(runningTeam.getAcademicLevel().getCode(), pair);
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