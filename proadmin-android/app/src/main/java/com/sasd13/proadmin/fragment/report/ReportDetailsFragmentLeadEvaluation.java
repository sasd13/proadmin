package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ReportDetailsFragmentLeadEvaluation extends Fragment implements Observer {

    private IReportController controller;
    private Report report;
    private List<StudentTeam> studentTeams;
    private LeadEvaluationForm leadEvaluationForm;

    public static ReportDetailsFragmentLeadEvaluation newInstance(ReportWrapper reportWrapper) {
        ReportDetailsFragmentLeadEvaluation fragment = new ReportDetailsFragmentLeadEvaluation();
        fragment.report = reportWrapper.getReport();
        fragment.studentTeams = reportWrapper.getStudentTeams();

        reportWrapper.addObserver(fragment);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
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
        bindFormWithStudents();
    }

    private void buildFormLeadEvaluation(View view) {
        leadEvaluationForm = new LeadEvaluationForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
    }

    private void bindFormWithLeadEvaluation() {
        leadEvaluationForm.bindLeadEvaluation(report.getLeadEvaluation());
    }

    private void bindFormWithStudents() {
        List<Student> students = new StudentsFromStudentTeamBuilder(studentTeams).build();

        leadEvaluationForm.bindLeader(students, report.getLeadEvaluation().getStudent());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.menu_edit_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeam() {
        try {
            LeadEvaluation leadEvaluationFromForm = new LeadEvaluationFromFormBuilder(leadEvaluationForm).build();

            leadEvaluationFromForm.setReport(report);
            controller.updateLeadEvaluation(leadEvaluationFromForm, report.getLeadEvaluation());
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        ReportWrapper reportWrapper = (ReportWrapper) observable;

        studentTeams = reportWrapper.getStudentTeams();

        bindFormWithStudents();
    }
}