package com.sasd13.proadmin.activity.fragment.report;

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
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.content.extra.running.ReportParcel;
import com.sasd13.proadmin.content.extra.running.RunningTeamParcel;

public class ReportDetailsFragment extends Fragment {

    private ReportsActivity parentActivity;

    private Report report;

    public static ReportDetailsFragment newInstance(Report report) {
        ReportDetailsFragment fragment = new ReportDetailsFragment();
        fragment.report = report;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readFromBundle(savedInstanceState);

        parentActivity = (ReportsActivity) getActivity();
    }

    private void readFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        if (report == null) {
            report = savedInstanceState.getParcelable(Extra.PARCEL_REPORT);
        }
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
        Pager pager = new Pager(viewPager, getChildFragmentManager(), new ReportPagerFragmentFactory(report));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        tabsStrip.setViewPager(viewPager);
        parentActivity.setPagerHandler(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Extra.PARCEL_REPORT, new ReportParcel(report));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        parentActivity.setPagerHandler(null);
    }
}