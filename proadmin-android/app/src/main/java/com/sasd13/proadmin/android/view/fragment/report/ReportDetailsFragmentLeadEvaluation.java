package com.sasd13.proadmin.android.view.fragment.report;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.scope.ReportScope;
import com.sasd13.proadmin.android.util.builder.StudentsFromStudentTeamsBuilder;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.gui.form.LeadEvaluationForm;

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

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

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
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, leadEvaluationForm.getHolder());
    }

    private void bindFormWithLeadEvaluation(LeadEvaluation leadEvaluation, List<StudentTeam> studentTeams) {
        leadEvaluationForm.bindLeadEvaluation(leadEvaluation, new StudentsFromStudentTeamsBuilder(studentTeams).build());
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
                saveLeadEvaluation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void saveLeadEvaluation() {
        try {
            controller.actionSaveLeadEvaluation(getEditedLeadEvaluationFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private LeadEvaluation getEditedLeadEvaluationFromForm() throws FormException {
        LeadEvaluation leadEvaluation = scope.getLeadEvaluation();

        leadEvaluation.setPlanningMark(leadEvaluationForm.getPlanningMark());
        leadEvaluation.setPlanningComment(leadEvaluationForm.getPlanningComment());
        leadEvaluation.setCommunicationMark(leadEvaluationForm.getCommunicationMark());
        leadEvaluation.setCommunicationComment(leadEvaluationForm.getCommunicationComment());
        leadEvaluation.setStudent(leadEvaluationForm.getLeader());

        return leadEvaluation;
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithLeadEvaluation(scope.getLeadEvaluation(), scope.getStudentTeams());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}