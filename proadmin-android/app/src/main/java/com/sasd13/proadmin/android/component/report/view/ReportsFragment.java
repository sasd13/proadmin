package com.sasd13.proadmin.android.component.report.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.scope.ReportScope;
import com.sasd13.proadmin.android.util.sorter.ReportSorter;
import com.sasd13.proadmin.android.gui.tab.ReportItemModel;
import com.sasd13.proadmin.util.EnumPreference;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportsFragment extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private Recycler recycler;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_srl_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildTabReports(view);
        buildSwipeRefreshLayout(view);
        buildFloatingActionButton(view);
        bindTabWithReports(scope.getReports());
    }

    private void buildTabReports(View view) {
        recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_srl_fab_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void buildSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_rv_w_srl_fab_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                controller.actionReadReports();
            }
        });
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_srl_fab_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.actionNewReport();
            }
        });
    }

    private void bindTabWithReports(List<Report> reports) {
        ReportSorter.byDateMeeting(reports);
        addReportsToTab(reports);
    }

    private void addReportsToTab(List<Report> reports) {
        RecyclerHolder holder = new RecyclerHolder();
        RecyclerHolderPair pair;
        String patternDate = scope.getUserPreferences().findValue(EnumPreference.GENERAL_DATE);

        for (final Report report : reports) {
            pair = new RecyclerHolderPair(new ReportItemModel(report, getContext()));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    controller.actionShowReport(report);
                }
            });

            holder.add(new SimpleDateFormat(patternDate).format(report.getDateMeeting()), pair);
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
        swipeRefreshLayout.setRefreshing(scope.isLoading());
        bindTabWithReports(scope.getReportsToAdd());
    }

    @Override
    public void onPause() {
        super.onPause();

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.destroyDrawingCache();
        swipeRefreshLayout.clearAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}