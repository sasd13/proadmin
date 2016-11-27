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
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsFormException;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.IndividualEvaluationsService;

import java.util.List;

public class ReportDetailsFragmentIndividualEvaluations extends Fragment implements IndividualEvaluationsService.ManageCaller {

    private ReportsActivity parentActivity;

    private IndividualEvaluationsForm individualEvaluationsForm;

    private Report report;

    private IndividualEvaluationsService service;

    public static ReportDetailsFragmentIndividualEvaluations newInstance(Report report) {
        ReportDetailsFragmentIndividualEvaluations fragment = new ReportDetailsFragmentIndividualEvaluations();
        fragment.report = report;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
        service = new IndividualEvaluationsService(this);
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
        individualEvaluationsForm.bindIndividualEvaluations(report.getIndividualEvaluations());
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
            service.update(individualEvaluationsForm.getIndividualEvaluations(), report.getIndividualEvaluations());
        } catch (IndividualEvaluationsFormException e) {
            displayError(e.getMessage());
        }
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onCreated() {
    }

    @Override
    public void onUpdated() {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleted() {
    }

    @Override
    public void onError(List<String> errors) {
        displayError(WebServiceUtils.handleErrors(getContext(), errors));
    }

    public void displayError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}