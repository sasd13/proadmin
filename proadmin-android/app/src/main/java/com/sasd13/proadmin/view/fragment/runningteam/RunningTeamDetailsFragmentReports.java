package com.sasd13.proadmin.view.fragment.runningteam;

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
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.view.gui.tab.ReportItemModel;
import com.sasd13.proadmin.util.sorter.running.ReportsSorter;
import com.sasd13.proadmin.scope.RunningTeamWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamDetailsFragmentReports extends Fragment implements Observer {

    private IReportController controller;
    private RunningTeam runningTeam;
    private List<Report> reports;
    private Recycler recycler;

    public static RunningTeamDetailsFragmentReports newInstance(RunningTeamWrapper runningTeamWrapper) {
        RunningTeamDetailsFragmentReports fragment = new RunningTeamDetailsFragmentReports();
        fragment.runningTeam = runningTeamWrapper.getRunningTeam();
        fragment.reports = runningTeamWrapper.getReports();

        runningTeamWrapper.addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
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
        bindTabWithReports(reports);
    }

    private void buildTabReports(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();
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

        RecyclerHelper.addAll(recycler, holder);
    }

    @Override
    public void update(Observable observable, Object o) {
        RunningTeamWrapper runningTeamWrapper = (RunningTeamWrapper) observable;

        if (!reports.containsAll(runningTeamWrapper.getReports())) {
            addNextReports(runningTeamWrapper.getReports());
        }
    }

    private void addNextReports(List<Report> nextReports) {
        reports.addAll(nextReports);
        bindTabWithReports(nextReports);
    }
}