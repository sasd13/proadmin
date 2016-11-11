package com.sasd13.proadmin.activity.fragment.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.TeamsActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.tab.TeamItemModel;
import com.sasd13.proadmin.service.member.TeamReadService;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

import java.util.ArrayList;
import java.util.List;

public class TeamsFragment extends Fragment implements IReadServiceCaller<IReadWrapper<Team>> {

    private TeamsActivity parentActivity;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Recycler teamsTab;

    private List<Team> teams;

    private TeamReadService teamReadService;

    public static TeamsFragment newInstance() {
        return new TeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (TeamsActivity) getActivity();
        teams = new ArrayList<>();
        teamReadService = new TeamReadService(this);
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
        buildTabRunnings(view);
        buildFloatingActionButton(view);
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_fab_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readTeamsFromWS();
            }
        });
    }

    private void readTeamsFromWS() {
        teamReadService.readTeams();
    }

    private void buildTabRunnings(View view) {
        teamsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        teamsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_srl_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newTeam();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_teams));
        readTeamsFromWS();
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Team> teamReadWrapper) {
        swipeRefreshLayout.setRefreshing(false);
        bindTeams(teamReadWrapper.getWrapped());
    }

    private void bindTeams(List<Team> teamFromWS) {
        teams.clear();
        teams.addAll(teamFromWS);
        fillTabTeamsByYear();
    }

    private void fillTabTeamsByYear() {
        teamsTab.clear();

        TeamsSorter.byNumber(teams);
        addRunningsToTab();
    }

    private void addRunningsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Team team : teams) {
            pair = new RecyclerHolderPair(new TeamItemModel(team));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showTeam(team);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(teamsTab, holder);
    }

    @Override
    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}