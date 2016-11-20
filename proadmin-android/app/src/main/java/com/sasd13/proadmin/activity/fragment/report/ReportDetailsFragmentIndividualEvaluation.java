package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.service.running.IndividualEvaluationsManageService;

public class ReportDetailsFragmentIndividualEvaluation extends Fragment implements IManageServiceCaller<IndividualEvaluation> {

    private ReportsActivity parentActivity;

    private IndividualEvaluationsForm individualEvaluationsForm;

    private Report report;

    private IndividualEvaluationsManageService individualEvaluationsManageService;

    public static ReportDetailsFragmentIndividualEvaluation newInstance(Report report) {
        ReportDetailsFragmentIndividualEvaluation fragment = new ReportDetailsFragmentIndividualEvaluation();
        fragment.report = report;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ReportsActivity) getActivity();
        individualEvaluationsManageService = new IndividualEvaluationsManageService(this);
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
        individualEvaluationsManageService.update(individualEvaluationsForm, report.getIndividualEvaluations());
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onCreateSucceeded(IndividualEvaluation individualEvaluation) {
    }

    @Override
    public void onUpdateSucceeded() {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucceeded() {
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}