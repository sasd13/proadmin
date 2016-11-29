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
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.member.StudentsFromStudentTeamBuilder;
import com.sasd13.proadmin.util.builder.running.IndividualEvaluationsBuilder;
import com.sasd13.proadmin.util.builder.running.LeadEvaluationFromFormBuilder;
import com.sasd13.proadmin.util.builder.running.StudentsOfReportBuilder;
import com.sasd13.proadmin.ws.service.StudentsService;

import java.util.List;

public class ReportNewFragmentLeadEvaluation extends Fragment implements StudentsService.ReadCaller {

    private ReportsActivity parentActivity;
    private ReportNewFragment parentFragment;

    private LeadEvaluationForm leadEvaluationForm;

    private Report report;

    private StudentsService service;

    public static ReportNewFragmentLeadEvaluation newInstance(ReportNewFragment parentFragment, Report report) {
        ReportNewFragmentLeadEvaluation fragment = new ReportNewFragmentLeadEvaluation();
        fragment.parentFragment = parentFragment;
        fragment.report = report;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
        service = new StudentsService(this);
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
        buildFormLeadEvaluation(view);
        bindFormWithLeadEvaluation();
    }

    private void buildFormLeadEvaluation(View view) {
        leadEvaluationForm = new LeadEvaluationForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
    }

    private void bindFormWithLeadEvaluation() {
        leadEvaluationForm.bindLeadEvaluation(report.getLeadEvaluation());
        leadEvaluationForm.bindLeader(new StudentsOfReportBuilder(report).build(), report.getLeadEvaluation().getStudent());
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
                createLeadEvaluation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createLeadEvaluation() {
        try {
            LeadEvaluation leadEvaluation = getLeadEvaluationFromForm();

            leadEvaluation.setReport(report);
            report.setLeadEvaluation(leadEvaluation);

            parentFragment.forward();
        } catch (FormException e) {
            displayMessage(e.getMessage());
        }
    }

    private LeadEvaluation getLeadEvaluationFromForm() throws FormException {
        return new LeadEvaluationFromFormBuilder(leadEvaluationForm).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        service.read(report.getRunningTeam().getTeam());
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<StudentTeam> studentTeams) {
        leadEvaluationForm.bindLeader(new StudentsFromStudentTeamBuilder(studentTeams).build());
        report.setIndividualEvaluations(new IndividualEvaluationsBuilder(report, studentTeams).build());
    }

    @Override
    public void onErrors(List<String> errors) {
        displayMessage(WebServiceUtils.handleErrors(getContext(), errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}