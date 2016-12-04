package com.sasd13.proadmin.view.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;

import java.util.List;

public class ReportNewFragment extends Fragment {

    private IReportController controller;
    private ReportNewPagerFragmentFactory fragmentFactory;
    private Pager pager;
    private Report reportToCreate;

    public static ReportNewFragment newInstance(IReportController controller) {
        ReportNewFragment fragment = new ReportNewFragment();
        fragment.controller = controller;
        fragment.reportToCreate = new DefaultReportBuilder().build();

        return fragment;
    }

    public static ReportNewFragment newInstance(IReportController controller, RunningTeam runningTeam) {
        ReportNewFragment fragment = newInstance(controller);
        fragment.reportToCreate.setRunningTeam(runningTeam);

        return fragment;
    }

    public Report getReportToCreate() {
        return reportToCreate;
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
        fragmentFactory = new ReportNewPagerFragmentFactory(getChildFragmentManager(), controller, this);

        pager.setAdapter(fragmentFactory);
        pager.setScrollable(false);
        ((MainActivity) getActivity()).setPager(pager);
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        fragmentFactory.setRunningTeams(runningTeams);
    }

    public void setDependencyWrapper(ReportDependencyWrapper dependencyWrapper) {
        fragmentFactory.setDependencyWrapper(dependencyWrapper);
    }

    public void forward() {
        if (reportToCreate.getRunningTeam() != null) {
            pager.forward();
        } else {
            controller.displayMessage(getResources().getString(R.string.error_no_runningteam_selected));
        }
    }

    public void createReport() {
        controller.createReport(reportToCreate);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
    }
}