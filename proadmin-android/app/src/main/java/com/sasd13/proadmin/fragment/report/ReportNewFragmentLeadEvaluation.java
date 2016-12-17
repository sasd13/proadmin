package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.util.builder.member.StudentsFromStudentTeamBuilder;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportNewFragmentLeadEvaluation extends Fragment implements Observer {

    private IReportController controller;
    private Report report;
    private List<StudentTeam> studentTeams;
    private ReportNewFragment parentFragment;
    private LeadEvaluationForm leadEvaluationForm;

    public static ReportNewFragmentLeadEvaluation newInstance(ReportWrapper reportWrapper, ReportNewFragment parentFragment) {
        ReportNewFragmentLeadEvaluation fragment = new ReportNewFragmentLeadEvaluation();
        fragment.report = reportWrapper.getReport();
        fragment.studentTeams = reportWrapper.getStudentTeams();
        fragment.parentFragment = parentFragment;

        reportWrapper.addObserver(fragment);

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
        buildFormLeadEvaluation(view);
        buildFloatingActionButton(view);
        bindFormWithLeadEvaluation(report.getLeadEvaluation());
        bindFormWithStudents();
    }

    private void buildFormLeadEvaluation(View view) {
        leadEvaluationForm = new LeadEvaluationForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
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
                    controller.displayMessage(e.getMessage());
                }
            }
        });
    }

    private void editLeadEvaluationWithForm() throws FormException {
        LeadEvaluation leadEvaluationFromForm = new LeadEvaluationFromFormBuilder(leadEvaluationForm).build();

        leadEvaluationFromForm.setReport(report);
        report.setLeadEvaluation(leadEvaluationFromForm);
    }

    private void goForward() {
        parentFragment.forward();
    }

    private void bindFormWithLeadEvaluation(LeadEvaluation leadEvaluation) {
        leadEvaluationForm.bindLeadEvaluation(leadEvaluation);
    }

    private void bindFormWithStudents() {
        List<Student> students = new StudentsFromStudentTeamBuilder(studentTeams).build();

        leadEvaluationForm.bindLeader(students);
    }

    @Override
    public void update(Observable observable, Object o) {
        ReportWrapper reportWrapper = (ReportWrapper) observable;

        studentTeams = reportWrapper.getStudentTeams();

        bindFormWithStudents();
    }
}