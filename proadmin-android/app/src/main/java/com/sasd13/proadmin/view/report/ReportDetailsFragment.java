package com.sasd13.proadmin.view.report;

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
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
import com.sasd13.proadmin.view.IReportController;

import java.util.List;

public class ReportDetailsFragment extends Fragment {

    private IReportController controller;
    private Report report;
    private ReportDetailsPagerFragmentFactory fragmentFactory;

    public static ReportDetailsFragment newInstance(IReportController controller, Report report) {
        ReportDetailsFragment fragment = new ReportDetailsFragment();
        fragment.controller = controller;
        fragment.report = report;

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
        fragmentFactory = new ReportDetailsPagerFragmentFactory(getChildFragmentManager(), controller, getContext(), report);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(fragmentFactory);
        tabsStrip.setViewPager(pager);
        ((MainActivity) getActivity()).setPager(pager);
    }

    public void setLeadEaluation(LeadEvaluation leadEaluations) {
        fragmentFactory.setLeadEvaluation(leadEaluations);
    }

    public void setDependencyWrapper(ReportDependencyWrapper dependencyWrapper) {
        fragmentFactory.setDependencyWrapper(dependencyWrapper);
    }

    public void setIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        fragmentFactory.setIndividualEvaluations(individualEvaluations);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPager(null);
    }
}