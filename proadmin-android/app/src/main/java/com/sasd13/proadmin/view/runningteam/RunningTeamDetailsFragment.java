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
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;

import java.util.List;

public class RunningTeamDetailsFragment extends Fragment {

    private IRunningTeamController controller;
    private RunningTeam runningTeam;
    private RunningTeamPagerFragmentFactory fragmentFactory;

    public static RunningTeamDetailsFragment newInstance(IRunningTeamController controller, RunningTeam runningTeam) {
        RunningTeamDetailsFragment fragment = new RunningTeamDetailsFragment();
        fragment.controller = controller;
        fragment.runningTeam = runningTeam;

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
        fragmentFactory = new RunningTeamPagerFragmentFactory(getChildFragmentManager(), controller, getContext(), runningTeam);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(fragmentFactory);
        tabsStrip.setViewPager(pager);
        ((MainActivity) getActivity()).setPager(pager);
    }

    public void setDependencyWrapper(RunningTeamDependencyWrapper dependencyWrapper) {
        fragmentFactory.setDependencyWrapper(dependencyWrapper);
    }

    public void setReports(List<Report> reports) {
        fragmentFactory.setReports(reports);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteam));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPager(null);
    }
}