package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.fragment.IReportController;

import java.util.List;

public class ReportNewFragment extends Fragment implements IPagerHandler {

    private IReportController controller;
    private ReportNewPagerFactory fragmentFactory;
    private Report reportToCreate;
    private Pager pager;

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
    public boolean handleBackPress() {
        return pager.handleBackPress(this);
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
        fragmentFactory = new ReportNewPagerFactory(this, controller);

        pager.setAdapter(fragmentFactory);
        pager.setScrollable(false);
        ((MainActivity) getActivity()).setPagerHandler(this);
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        fragmentFactory.setRunningTeams(runningTeams);
    }

    public void setReportWrapper(ReportWrapper reportWrapper) {
        fragmentFactory.setReportWrapper(reportWrapper);
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
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPagerHandler(null);
    }
}