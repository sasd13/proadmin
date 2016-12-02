package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.ws.service.ReportsService;

import java.util.List;

public class ReportNewFragment extends Fragment implements ReportsService.ManageCaller {

    private ReportsActivity parentActivity;

    private Pager pager;

    private Report reportToCreate;

    private ReportsService service;

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

        parentActivity = (ReportsActivity) getActivity();
        service = new ReportsService(this);
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
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.layout_vp_viewpager);
        pager = new Pager(viewPager, new ReportNewPagerFragmentFactory(getChildFragmentManager(), this));

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