package com.sasd13.proadmin.view.fragment.report;

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
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.scope.ReportScope;
import com.sasd13.proadmin.util.builder.member.StudentsFromStudentTeamBuilder;
import com.sasd13.proadmin.view.gui.form.LeadEvaluationForm;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ReportDetailsFragmentLeadEvaluation extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private LeadEvaluationForm leadEvaluationForm;
    private Menu menu;

    public static ReportDetailsFragmentLeadEvaluation newInstance() {
        return new ReportDetailsFragmentLeadEvaluation();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();

        scope.addObserver(this);
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
        bindFormWithLeadEvaluation(scope.getLeadEvaluation(), scope.getStudentTeams());
    }

    private void buildFormLeadEvaluation(View view) {
        leadEvaluationForm = new LeadEvaluationForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
    }

    private void bindFormWithLeadEvaluation(LeadEvaluation leadEvaluation, List<StudentTeam> studentTeams) {
        leadEvaluationForm.bindLeadEvaluation(leadEvaluation, new StudentsFromStudentTeamBuilder(studentTeams).build());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

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
                saveTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void saveTeam() {
        try {
            if (scope.getLeadEvaluation() != null) {
                controller.actionUpdateLeadEvaluation(getLeadEvaluationFromForm(), scope.getLeadEvaluation());
            } else {
                controller.actionCreateLeadEvaluation(getLeadEvaluationFromForm());
            }
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private LeadEvaluation getLeadEvaluationFromForm() throws FormException {
        LeadEvaluation leadEvaluationFromForm = new LeadEvaluation();

        leadEvaluationFromForm.setPlanningMark(leadEvaluationForm.getPlanningMark());
        leadEvaluationFromForm.setPlanningComment(leadEvaluationForm.getPlanningComment());
        leadEvaluationFromForm.setCommunicationMark(leadEvaluationForm.getCommunicationMark());
        leadEvaluationFromForm.setCommunicationComment(leadEvaluationForm.getCommunicationComment());
        leadEvaluationFromForm.setReport(scope.getReport());
        leadEvaluationFromForm.setStudent(leadEvaluationForm.getLeader());

        return leadEvaluationFromForm;
    }

    @Override
    public void update(Observable observable, Object o) {
        scope = (ReportScope) observable;

        bindFormWithLeadEvaluation(scope.getLeadEvaluation(), scope.getStudentTeams());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}