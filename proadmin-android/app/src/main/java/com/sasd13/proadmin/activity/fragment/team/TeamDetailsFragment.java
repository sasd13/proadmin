package com.sasd13.proadmin.activity.fragment.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.TeamsActivity;
import com.sasd13.proadmin.bean.member.Team;

public class TeamDetailsFragment extends Fragment {

    private TeamsActivity parentActivity;

    private Team team;

    public static TeamDetailsFragment newInstance(Team team) {
        TeamDetailsFragment fragment = new TeamDetailsFragment();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (TeamsActivity) getActivity();
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
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.layout_vp_w_psts_viewpager);
        Pager pager = new Pager(viewPager, getChildFragmentManager(), new TeamPagerFragmentFactory(team));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        tabsStrip.setViewPager(viewPager);
        parentActivity.setPagerHandler(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_team));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        parentActivity.setPagerHandler(null);
    }
}