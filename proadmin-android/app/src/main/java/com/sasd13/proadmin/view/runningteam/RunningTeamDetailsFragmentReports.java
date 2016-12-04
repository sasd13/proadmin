package com.sasd13.proadmin.view.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.RunningTeamsActivity;
import com.sasd13.proadmin.gui.tab.ReportItemModel;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.sorter.running.ReportsSorter;
import com.sasd13.proadmin.ws.service.ReportsService;

import java.util.ArrayList;
import java.util.List;

public class RunningTeamDetailsFragmentReports extends Fragment implements ReportsService.ReadCaller {

    private RunningTeamsActivity parentActivity;

    private Recycler reportsTab;

    private RunningTeam runningTeam;
    private List<Report> reports;

    private ReportsService service;

    public static RunningTeamDetailsFragmentReports newInstance(RunningTeam runningTeam) {
        RunningTeamDetailsFragmentReports fragment = new RunningTeamDetailsFragmentReports();
        fragment.runningTeam = runningTeam;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (RunningTeamsActivity) getActivity();
        reports = new ArrayList<>();
        service = new ReportsService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabReports(view);
        buildFloatingActionButton(view);
    }

    private void buildTabReports(View view) {
        reportsTab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        reportsTab.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newReport(runningTeam);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        readReportsFromWS();
    }

    private void readReportsFromWS() {
        service.read(runningTeam);
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<Report> reportsFromWS) {
        reports.clear();
        reports.addAll(reportsFromWS);
        fillTabReportsByTeam();
    }

    private void fillTabReportsByTeam() {
        reportsTab.clear();
        ReportsSorter.byNumber(reports);
        addReportsToTab();
    }

    private void addReportsToTab() {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Report report : reports) {
            pair = new RecyclerHolderPair(new ReportItemModel(report, getContext()));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.showReport(report);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(reportsTab, holder);
    }

    @Override
    public void onErrors(List<String> errors) {
        displayMessage(WebServiceUtils.handleErrors(getContext(), errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}