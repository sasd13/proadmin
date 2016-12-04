package com.sasd13.proadmin.view.runningteam;

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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.RunningTeamsActivity;

public class RunningTeamDetailsFragment extends Fragment {

    private RunningTeamsActivity parentActivity;

    private RunningTeam runningTeam;

    public static RunningTeamDetailsFragment newInstance(RunningTeam runningTeam) {
        RunningTeamDetailsFragment fragment = new RunningTeamDetailsFragment();
        fragment.runningTeam = runningTeam;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (RunningTeamsActivity) getActivity();
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

        pager.setAdapter(new RunningTeamPagerFragmentFactory(getChildFragmentManager(), getContext(), runningTeam));
        tabsStrip.setViewPager(pager);
        parentActivity.setPager(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteam));
        parentActivity.getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        parentActivity.setPager(null);
    }
}