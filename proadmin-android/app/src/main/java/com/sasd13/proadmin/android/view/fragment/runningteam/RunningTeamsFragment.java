package com.sasd13.proadmin.android.view.fragment.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.scope.RunningTeamScope;
import com.sasd13.proadmin.android.util.sorter.RunningTeamSorter;
import com.sasd13.proadmin.android.view.IRunningTeamController;
import com.sasd13.proadmin.android.view.gui.tab.RunningTeamItemModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamsFragment extends Fragment implements Observer {

    private IRunningTeamController controller;
    private RunningTeamScope scope;
    private Recycler recycler;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static RunningTeamsFragment newInstance() {
        return new RunningTeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IRunningTeamController) ((MainActivity) getActivity()).lookup(IRunningTeamController.class);
        scope = (RunningTeamScope) controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_srl_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabRunningTeams(view);
        buildSwipeRefreshLayout(view);
        buildFloatingActionButton(view);
        bindTabWithRunningTeams(scope.getRunningTeams());
    }

    private void buildTabRunningTeams(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_fab_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                controller.actionLoadRunningTeams();
            }
        });
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_srl_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.actionNewRunningTeam();
            }
        });
    }

    private void bindTabWithRunningTeams(List<RunningTeam> runningTeams) {
        RunningTeamSorter.byAcademicLevelCode(runningTeams);
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
                    controller.actionShowRunningTeam(runningTeam);
                }
            });

            holder.add(runningTeam.getAcademicLevel().getCode(), pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_runningteams));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        swipeRefreshLayout.setRefreshing(scope.isLoading());
        bindTabWithRunningTeams(scope.getRunningTeamsToAdd());
    }

    @Override
    public void onPause() {
        super.onPause();

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.destroyDrawingCache();
        swipeRefreshLayout.clearAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}