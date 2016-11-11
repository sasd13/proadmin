package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
import com.sasd13.proadmin.activity.RunningsActivity;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.service.running.RunningTeamReadService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment implements IReadServiceCaller<IReadWrapper<RunningTeam>> {

    private RunningsActivity parentActivity;

    private Recycler runningTeamsTab;

    private Running running;
    private List<RunningTeam> runningTeams;

    private RunningTeamReadService runningTeamReadService;

    public static ReportsFragment newInstance(Running running) {
        ReportsFragment fragment = new ReportsFragment();
        fragment.running = running;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        runningTeams = new ArrayList<>();
        runningTeamReadService = new RunningTeamReadService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabRunnings(view);
    }

    private void buildTabRunnings(View view) {
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_reports));
        readRunningTeamsFromWS();
    }

    private void readRunningTeamsFromWS() {
        runningTeamReadService.readRunningTeams(
                running.getYear(),
                running.getProject().getCode(),
                SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadSucceeded(IReadWrapper<RunningTeam> runningTeamReadWrapper) {
        runningTeams.clear();
        runningTeams.addAll(runningTeamReadWrapper.getWrapped());
        fillTabRunningTeamsByTeam();
    }

    private void fillTabRunningTeamsByTeam() {
        runningTeamsTab.clear();
        RunningTeamsSorter.byTeamNumber(runningTeams);
        addRunningsToTab();
    }

    private void addRunningsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final RunningTeam runningTeam : runningTeams) {
            pair = new RecyclerHolderPair(new RunningTeamItemModel(runningTeam));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showTeam(runningTeam.getTeam());
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}