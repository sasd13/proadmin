package com.sasd13.proadmin.fragment.team;

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
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.ITeamController;
import com.sasd13.proadmin.gui.tab.TeamItemModel;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.wrapper.TeamsWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TeamsFragment extends Fragment implements Observer {

    private ITeamController controller;
    private List<Team> teams;
    private Recycler recycler;

    public static TeamsFragment newInstance(TeamsWrapper teamsWrapper) {
        TeamsFragment fragment = new TeamsFragment();
        fragment.teams = teamsWrapper.getTeams();

        teamsWrapper.addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ITeamController) ((MainActivity) getActivity()).lookup(ITeamController.class);
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
        buildTabRunnings(view);
        buildFloatingActionButton(view);
        bindTabWithTeams(teams);
    }

    private void buildTabRunnings(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.newTeam();
            }
        });
    }

    private void bindTabWithTeams(List<Team> teams) {
        TeamsSorter.byNumber(teams);
        addRunningsToTab(teams);
    }

    private void addRunningsToTab(List<Team> teams) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Team team : teams) {
            pair = new RecyclerHolderPair(new TeamItemModel(team));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.showTeam(team);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_teams));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        TeamsWrapper teamsWrapper = (TeamsWrapper) observable;

        if (!teams.containsAll(teamsWrapper.getTeams())) {
            addNextTeams(teamsWrapper.getTeams());
        }
    }

    private void addNextTeams(List<Team> nextTeams) {
        teams.addAll(nextTeams);
        bindTabWithTeams(nextTeams);
    }
}