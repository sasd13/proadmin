package com.sasd13.proadmin.activity.fragment.running;

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
import com.sasd13.proadmin.activity.RunningsActivity;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.content.extra.running.RunningParcel;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.service.running.RunningTeamReadService;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class RunningDetailsFragmentRunningTeams extends Fragment implements IReadServiceCaller<IReadWrapper<RunningTeam>> {

    private RunningsActivity parentActivity;

    private Recycler runningTeamsTab;

    private Running running;
    private List<RunningTeam> runningTeams;

    private RunningTeamReadService runningTeamReadService;

    public static RunningDetailsFragmentRunningTeams newInstance(Running running) {
        RunningDetailsFragmentRunningTeams fragment = new RunningDetailsFragmentRunningTeams();
        fragment.running = running;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readFromBundle(savedInstanceState);

        parentActivity = (RunningsActivity) getActivity();
        runningTeams = new ArrayList<>();
        runningTeamReadService = new RunningTeamReadService(this);
    }

    private void readFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        if (running == null) {
            running = savedInstanceState.getParcelable(Extra.PARCEL_RUNNING);
        }
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
    }

    private void buildTabRunningTeams(View view) {
        runningTeamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        runningTeamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newRunningTeam(running);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        readRunningTeamsFromWS();
    }

    private void readRunningTeamsFromWS() {
        runningTeamReadService.readRunningTeams(running);
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
        addRunningTeamsToTab();
    }

    private void addRunningTeamsToTab() {
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

            holder.add(pair);
        }

        RecyclerHelper.addAll(runningTeamsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Extra.PARCEL_RUNNING, new RunningParcel(running));
    }
}