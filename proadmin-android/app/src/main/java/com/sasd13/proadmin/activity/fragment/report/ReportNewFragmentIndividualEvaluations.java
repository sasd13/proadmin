package com.sasd13.proadmin.activity.fragment.report;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormIndividualEvaluations(view);
        buildFloatingActionButton(view);
    }

    private void buildFormIndividualEvaluations(View view) {
        individualEvaluationsForm = new IndividualEvaluationsForm(getContext());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        recycler.addDividerItemDecoration();

        bindFormWithIndividualEvaluations();
        RecyclerHelper.addAll(recycler, individualEvaluationsForm.getHolder());
    }

    private void bindFormWithIndividualEvaluations() {
        individualEvaluationsForm.bindIndividualEvaluations(parentFragment.getReportToCreate().getIndividualEvaluations());
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_save_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editIndividualEvaluationsWithForm();
                    createReport();
                } catch (FormException e) {
                    displayMessage(e.getMessage());
                }
            }
        });
    }

    private void editIndividualEvaluationsWithForm() throws IndividualEvaluationsFormException {
        List<IndividualEvaluation> individualEvaluationsFromForm = individualEvaluationsForm.getIndividualEvaluations();
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