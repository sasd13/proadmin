package com.sasd13.proadmin.view.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.sasd13.proadmin.gui.tab.ReportItemModel;
import com.sasd13.proadmin.util.sorter.running.ReportsSorter;
import com.sasd13.proadmin.view.IRunningTeamController;

import java.util.List;

public class RunningTeamDetailsFragmentReports extends Fragment {

    private IRunningTeamController controller;
    private RunningTeam runningTeam;
    private Recycler reportsTab;

    public static RunningTeamDetailsFragmentReports newInstance(IRunningTeamController controller, RunningTeam runningTeam) {
        RunningTeamDetailsFragmentReports fragment = new RunningTeamDetailsFragmentReports();
        fragment.controller = controller;
        fragment.runningTeam = runningTeam;

        return fragment;
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
                controller.newReport(runningTeam);
            }
        });
    }

    public void setReports(List<Report> reports) {
        bindTabWithReports(reports);
    }

    private void bindTabWithReports(List<Report> reports) {
        ReportsSorter.byNumber(reports);
        addReportsToTab(reports);
    }

    private void addReportsToTab(List<Report> reports) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final Report report : reports) {
            pair = new RecyclerHolderPair(new ReportItemModel(report, getContext()));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.showReport(report);
                }
            });

            holder.add(pair);
        }

        RecyclerHelper.addAll(reportsTab, holder);
    }
}