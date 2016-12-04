package com.sasd13.proadmin.view.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.javaex.util.sorter.StringsSorter;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.controller.ReportsActivity;
import com.sasd13.proadmin.gui.tab.ReportItemModel;
import com.sasd13.proadmin.util.builder.running.ReportsTeamsNumbersBuilder;
import com.sasd13.proadmin.util.filter.running.ReportTeamCriteria;
import com.sasd13.proadmin.util.sorter.running.ReportsSorter;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment {

    private ReportsActivity parentActivity;

    private Spin spinTeams;
    private Recycler reportsTab;

    private List<String> teamsNumbers;
    private List<Report> reports;

    public static ReportsFragment newInstance(List<Report> reports) {
        ReportsFragment fragment = new ReportsFragment();
        fragment.reports = reports;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
        reports = new ArrayList<>();
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
        bindTeamsNumbers();
        bindReports();
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
                parentActivity.newReport();
            }
        });
    }

    private void bindTeamsNumbers() {
        teamsNumbers = new ReportsTeamsNumbersBuilder(reports).build();

        StringsSorter.byAsc(teamsNumbers);
        spinTeams.addItems(teamsNumbers);
        spinTeams.resetPosition();
    }

    private void bindReports() {
        reports.clear();
        reports.addAll(reports);
        fillTabReportsByTeam();
    }

    private void fillTabReportsByTeam() {
        reportsTab.clear();

        String teamNumber = teamsNumbers.get(spinTeams.getSelectedPosition());
        List<Report> reportsToTab = new ReportTeamCriteria(teamNumber).meetCriteria(reports);

        ReportsSorter.byNumber(reportsToTab);
        addReportsToTab(reportsToTab);
    }

    private void addReportsToTab(List<Report> reports) {
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_reports, menu);

        buildSpinYears(menu.findItem(R.id.menu_reports_spinner));
    }

    private void buildSpinYears(MenuItem menuItem) {
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menuItem);

        spinTeams = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabReportsByTeam();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_reports));
        parentActivity.getSupportActionBar().setSubtitle(null);
    }
}