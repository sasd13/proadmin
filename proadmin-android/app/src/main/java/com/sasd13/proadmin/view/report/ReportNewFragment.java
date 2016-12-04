package com.sasd13.proadmin.view.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.ws.service.ReportService;

import java.util.List;

public class ReportNewFragment extends Fragment implements ReportService.ManageCaller {

    private ReportController parentActivity;

    private Pager pager;

    private Report reportToCreate;

    private ReportService service;

    public static ReportNewFragment newInstance() {
        ReportNewFragment fragment = new ReportNewFragment();
        fragment.reportToCreate = new DefaultReportBuilder().build();

        return fragment;
    }

    public static ReportNewFragment newInstance(RunningTeam runningTeam) {
        ReportNewFragment fragment = newInstance();
        fragment.reportToCreate.setRunningTeam(runningTeam);

        return fragment;
    }

    public Report getReportToCreate() {
        return reportToCreate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ReportController) getActivity();
        service = new ReportService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_vp, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildPager(view);
    }

    private void buildPager(View view) {
        pager = (Pager) view.findViewById(R.id.layout_vp_viewpager);

        pager.setAdapter(new ReportNewPagerFragmentFactory(getChildFragmentManager(), this));
        pager.setScrollable(false);
        parentActivity.setPager(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
    }

    public void forward() {
        if (reportToCreate.getRunningTeam() != null) {
            pager.forward();
        } else {
            displayMessage(getResources().getString(R.string.error_no_runningteam_selected));
        }
    }

    public void createReport() {
        service.create(reportToCreate);
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onCreated() {
        parentActivity.listReports();
    }

    @Override
    public void onUpdated() {
    }

    @Override
    public void onDeleted() {
    }

    @Override
    public void onErrors(List<String> errors) {
        displayMessage(WebServiceUtils.handleErrors(getContext(), errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}