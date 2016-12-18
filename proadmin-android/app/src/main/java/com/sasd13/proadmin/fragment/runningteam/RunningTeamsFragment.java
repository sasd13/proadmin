package com.sasd13.proadmin.fragment.runningteam;

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
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.fragment.IRunningTeamController;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;
import com.sasd13.proadmin.util.sorter.running.RunningTeamsSorter;
import com.sasd13.proadmin.util.wrapper.RunningTeamsWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamsFragment extends Fragment implements Observer {

    private IRunningTeamController controller;
    private List<RunningTeam> runningTeams;
    private Recycler recycler;

    public static RunningTeamsFragment newInstance(RunningTeamsWrapper runningTeamsWrapper) {
        RunningTeamsFragment fragment = new RunningTeamsFragment();
        fragment.runningTeams = runningTeamsWrapper.getRunningTeams();

        runningTeamsWrapper.addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IRunningTeamController) ((MainActivity) getActivity()).lookup(IRunningTeamController.class);
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
        bindTabWithRunningTeams(runningTeams);
    }

    private void buildTabRunningTeams(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.newRunningTeam();
            }
        });
    }

    private void bindTabWithRunningTeams(List<RunningTeam> runningTeams) {
        RunningTeamsSorter.byAcademicLevelCode(runningTeams);
        addRunningTeamsToTab(runningTeams);
    }

    private void addRunningTeamsToTab(List<RunningTeam> runningTeams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final RunningTeam runningTeam : runningTeams) {
            pair = new RecyclerHolderPair(new RunningTeamItemModel(runningTeam));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.showRunningTeam(runningTeam);
                }
            });

            holder.add(runningTeam.getAcademicLevel().getCode(), pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteams));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        RunningTeamsWrapper runningTeamWrapper = (RunningTeamsWrapper) observable;

        if (!runningTeams.containsAll(runningTeamWrapper.getRunningTeams())) {
            addNextRunningTeams(runningTeamWrapper.getRunningTeams());
        }
    }

    private void addNextRunningTeams(List<RunningTeam> nextRunningTeams) {
        runningTeams.addAll(nextRunningTeams);
        bindTabWithRunningTeams(nextRunningTeams);
    }
}