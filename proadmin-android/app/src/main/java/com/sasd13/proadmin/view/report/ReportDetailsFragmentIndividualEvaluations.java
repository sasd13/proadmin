package com.sasd13.proadmin.view.report;

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
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.view.IReportController;

import java.util.List;

public class ReportDetailsFragmentIndividualEvaluations extends Fragment {

    private IReportController controller;
    private Report report;
    private IndividualEvaluationsForm individualEvaluationsForm;
    private Recycler recycler;

    public static ReportDetailsFragmentIndividualEvaluations newInstance(IReportController controller, ReportWrapper reportWrapper) {
        ReportDetailsFragmentIndividualEvaluations fragment = new ReportDetailsFragmentIndividualEvaluations();
        fragment.controller = controller;
        fragment.report = reportWrapper.getReport();

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
        bindFormWithIndividualEvaluations();
    }

    private void buildFormIndividualEvaluations(View view) {
        individualEvaluationsForm = new IndividualEvaluationsForm(getContext());

        recycler = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();
    }

    private void bindFormWithIndividualEvaluations() {
        individualEvaluationsForm.bindIndividualEvaluations(report.getIndividualEvaluations());
        RecyclerHelper.addAll(recycler, individualEvaluationsForm.getHolder());
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
                updateIndividualEvaluations();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateIndividualEvaluations() {
        try {
            List<IndividualEvaluation> individualEvaluationsFromForm = individualEvaluationsForm.getIndividualEvaluations();

            controller.updateIndividualEvaluations(individualEvaluationsFromForm, report.getIndividualEvaluations());
        } catch (IndividualEvaluationsFormException e) {
            controller.displayMessage(e.getMessage());
        }
    }
}