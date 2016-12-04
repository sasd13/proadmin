package com.sasd13.proadmin.view.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.TeamsActivity;

import java.util.List;

public class TeamDetailsFragment extends Fragment {

    private Team team;
    private List<StudentTeam> studentTeams;

    public static TeamDetailsFragment newInstance(Team team, List<StudentTeam> studentTeams) {
        TeamDetailsFragment fragment = new TeamDetailsFragment();
        fragment.team = team;
        fragment.studentTeams = studentTeams;

        return fragment;
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
        Pager pager = (Pager) view.findViewById(R.id.layout_vp_w_psts_viewpager);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(new TeamPagerFragmentFactory(getChildFragmentManager(), getContext(), team, studentTeams));
        tabsStrip.setViewPager(pager);
        ((TeamsActivity) getActivity()).setPager(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((TeamsActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_team));
        ((TeamsActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((TeamsActivity) getActivity()).setPager(null);
    }
}