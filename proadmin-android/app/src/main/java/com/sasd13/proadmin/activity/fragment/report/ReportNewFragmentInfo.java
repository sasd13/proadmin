package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.ReportForm;
import com.sasd13.proadmin.util.builder.running.ReportFromFormBuilder;

public class ReportNewFragmentInfo extends Fragment {

    private ReportsActivity parentActivity;
    private ReportNewFragment parentFragment;

    private ReportForm reportForm;

    private RunningTeam runningTeam;

    public static ReportNewFragmentInfo newInstance(ReportNewFragment parentFragment, RunningTeam runningTeam) {
        ReportNewFragmentInfo fragment = new ReportNewFragmentInfo();
        fragment.parentFragment = parentFragment;
        fragment.runningTeam = runningTeam;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);
    }

    private void buildFormRunning(View view) {
        reportForm = new ReportForm(getContext(), false);

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, reportForm.getHolder());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_report, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.setGroupVisible(R.id.menu_report_new_group_next, true);
        menu.setGroupVisible(R.id.menu_report_new_group_save, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_report_new_action_next:
                createReport();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createReport() {
        try {
            Report report = getReportFromForm();

            report.setRunningTeam(runningTeam);

            parentFragment.forward();
        } catch (FormException e) {
            displayMessage(e.getMessage());
        }
    }

    private Report getReportFromForm() throws FormException {
        return new ReportFromFormBuilder(reportForm).build();
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}