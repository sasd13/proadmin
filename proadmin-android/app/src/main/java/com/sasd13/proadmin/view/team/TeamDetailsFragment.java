package com.sasd13.proadmin.view.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.TeamDependencyWrapper;
import com.sasd13.proadmin.view.ITeamController;

public class TeamDetailsFragment extends Fragment implements IPagerHandler {

    private ITeamController controller;
    private Team team;
    private TeamDependencyWrapper dependencyWrapper;
    private Pager pager;

    public static TeamDetailsFragment newInstance(ITeamController controller, Team team, TeamDependencyWrapper dependencyWrapper) {
        TeamDetailsFragment fragment = new TeamDetailsFragment();
        fragment.controller = controller;
        fragment.team = team;
        fragment.dependencyWrapper = dependencyWrapper;

        return fragment;
    }

    @Override
    public boolean handleBackPress() {
        return pager.handleBackPress(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_vp_w_psts, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildPager(view);
    }

    private void buildPager(View view) {
        pager = (Pager) view.findViewById(R.id.layout_vp_w_psts_viewpager);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(new TeamDetailsPagerFactory(this, controller, team, dependencyWrapper));
        tabsStrip.setViewPager(pager);
        ((MainActivity) getActivity()).setPagerHandler(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_team));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPagerHandler(null);
    }
}