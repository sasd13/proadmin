package com.sasd13.proadmin.view.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.member.StudentsFromStudentTeamBuilder;
import com.sasd13.proadmin.util.builder.running.IndividualEvaluationsBuilder;
import com.sasd13.proadmin.util.builder.running.LeadEvaluationFromFormBuilder;
import com.sasd13.proadmin.ws.service.StudentService;

import java.util.List;

public class ReportNewFragmentLeadEvaluation extends Fragment implements StudentService.ReadCaller {

    private ReportNewFragment parentFragment;

    private LeadEvaluationForm leadEvaluationForm;

    private StudentService service;

    public static ReportNewFragmentLeadEvaluation newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentLeadEvaluation fragment = new ReportNewFragmentLeadEvaluation();
        fragment.parentFragment = parentFragment;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = new StudentService(this);
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
        buildFormLeadEvaluation(view);
        bindFormWithLeadEvaluation();
        buildFloatingActionButton(view);
    }

    private void buildFormLeadEvaluation(View view) {
        leadEvaluationForm = new LeadEvaluationForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
    }

    private void bindFormWithLeadEvaluation() {
        leadEvaluationForm.bindLeadEvaluation(parentFragment.getReportToCreate().getLeadEvaluation());
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_navigate_next_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editLeadEvaluationWithForm();
                    goForward();
                } catch (FormException e) {
                    displayMessage(e.getMessage());
                }
            }
        });
    }

    private void editLeadEvaluationWithForm() throws FormException {
        LeadEvaluation leadEvaluationFromForm = new LeadEvaluationFromFormBuilder(leadEvaluationForm).build();
        Report reportToCreate = parentFragment.getReportToCreate();

        leadEvaluationFromForm.setReport(reportToCreate);
        reportToCreate.setLeadEvaluation(leadEvaluationFromForm);
    }

    private void goForward() {
        parentFragment.forward();
    }

    @Override
    public void onStart() {
        super.onStart();

        service.read(parentFragment.getReportToCreate().getRunningTeam().getTeam());
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<StudentTeam> studentTeams) {
        Report reportToCreate = parentFragment.getReportToCreate();

        leadEvaluationForm.bindLeader(new StudentsFromStudentTeamBuilder(studentTeams).build());
        reportToCreate.setIndividualEvaluations(new IndividualEvaluationsBuilder(reportToCreate, studentTeams).build());
    }

    @Override
    public void onErrors(List<String> errors) {
        displayMessage(WebServiceUtils.handleErrors(getContext(), errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}