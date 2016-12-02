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

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsFormException;

import java.util.List;

public class ReportNewFragmentIndividualEvaluations extends Fragment {

    private ReportNewFragment parentFragment;

    private IndividualEvaluationsForm individualEvaluationsForm;

    public static ReportNewFragmentIndividualEvaluations newInstance(ReportNewFragment parentFragment) {
        ReportNewFragmentIndividualEvaluations fragment = new ReportNewFragmentIndividualEvaluations();
        fragment.parentFragment = parentFragment;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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
        buildFormIndividualEvaluations(view);
    }

    private void buildFormIndividualEvaluations(View view) {
        individualEvaluationsForm = new IndividualEvaluationsForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        bindFormWithIndividualEvaluations();
        RecyclerHelper.addAll(recycler, individualEvaluationsForm.getHolder());
    }

    private void bindFormWithIndividualEvaluations() {
        individualEvaluationsForm.bindIndividualEvaluations(parentFragment.getReportToCreate().getIndividualEvaluations());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_report_new, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.setGroupVisible(R.id.menu_report_new_group_next, false);
        menu.setGroupVisible(R.id.menu_report_new_group_save, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_report_new_action_save:
                editIndividualEvaluations();
                createReport();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void editIndividualEvaluations() {
        try {
            editIndividualEvaluationsWithForm(getIndividualEvaluationsFromForm());
        } catch (IndividualEvaluationsFormException e) {
            displayMessage(e.getMessage());
        }
    }

    private List<IndividualEvaluation> getIndividualEvaluationsFromForm() throws IndividualEvaluationsFormException {
        return individualEvaluationsForm.getIndividualEvaluations();
    }

    private void editIndividualEvaluationsWithForm(List<IndividualEvaluation> individualEvaluationsFromForm) {
        Report reportToCreate = parentFragment.getReportToCreate();

        reportToCreate.getIndividualEvaluations().clear();

        for (IndividualEvaluation individualEvaluationFromForm : individualEvaluationsFromForm) {
            individualEvaluationFromForm.setReport(reportToCreate);
            reportToCreate.getIndividualEvaluations().add(individualEvaluationFromForm);
        }
    }

    private void createReport() {
        parentFragment.createReport();
    }

    private void displayMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}