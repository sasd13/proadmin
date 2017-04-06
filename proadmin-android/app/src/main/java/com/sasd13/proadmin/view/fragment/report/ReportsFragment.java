package com.sasd13.proadmin.view.fragment.report;

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
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.scope.ReportScope;
import com.sasd13.proadmin.util.sorter.running.ReportsSorter;
import com.sasd13.proadmin.view.gui.tab.ReportItemModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportsFragment extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private Recycler recycler;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();

        scope.addObserver(this);
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
        bindTabWithReports(scope.getReports());
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
                controller.newReport();
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
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_reports));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        scope = (ReportScope) observable;

        bindTabWithReports(scope.getReports());

        /*if (!reports.containsAll(reportScope.getReports())) {
            addNextReports(reportScope.getReports());
        }*/
    }

    /*private void addNextReports(List<Report> nextReports) {
        reports.addAll(nextReports);
        bindTabWithReports(nextReports);
    }*/
}