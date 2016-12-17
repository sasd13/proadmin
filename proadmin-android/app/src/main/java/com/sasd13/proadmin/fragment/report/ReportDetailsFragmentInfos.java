package com.sasd13.proadmin.fragment.report;

import android.content.DialogInterface;
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
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.gui.form.ReportForm;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;

public class ReportDetailsFragmentInfos extends Fragment {

    private IReportController controller;
    private Report report;
    private ReportForm reportForm;

    public static ReportDetailsFragmentInfos newInstance(ReportWrapper reportWrapper) {
        ReportDetailsFragmentInfos fragment = new ReportDetailsFragmentInfos();
        fragment.report = reportWrapper.getReport();

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
        buildFormTeam(view);
        bindFormWithReport();
    }

    private void buildFormTeam(View view) {
        reportForm = new ReportForm(getContext(), true);

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, reportForm.getHolder());
    }

    private void bindFormWithReport() {
        reportForm.bindReport(report);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateReport();
                break;
            case R.id.menu_edit_action_delete:
                deleteReport();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateReport() {
        try {
            controller.updateReport(getReportFromForm(), report);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private Report getReportFromForm() throws FormException {
        Report reportFromForm = new Report();

        reportFromForm.setNumber(reportForm.getNumber());
        reportFromForm.setDateMeeting(reportForm.getDateMeeting());
        reportFromForm.setSession(reportForm.getSession());
        reportFromForm.setComment(reportForm.getComment());
        reportFromForm.setRunningTeam(report.getRunningTeam());
        reportFromForm.setLeadEvaluation(report.getLeadEvaluation());
        reportFromForm.setIndividualEvaluations(report.getIndividualEvaluations());

        return reportFromForm;
    }

    private void deleteReport() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.deleteReport(report);
                    }
                });
    }
}