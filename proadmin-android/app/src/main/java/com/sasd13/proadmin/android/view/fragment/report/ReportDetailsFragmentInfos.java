package com.sasd13.proadmin.android.view.fragment.report;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.UserPreference;
import com.sasd13.proadmin.android.scope.ReportScope;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.gui.form.ReportForm;

import java.util.Observable;
import java.util.Observer;

public class ReportDetailsFragmentInfos extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private UserPreference preferences;
    private ReportForm reportForm;
    private Menu menu;

    public static ReportDetailsFragmentInfos newInstance() {
        return new ReportDetailsFragmentInfos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();
        preferences = ((MainActivity) getActivity()).getPreferences();

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
        buildFormReport(view);
        bindFormWithReport(scope.getReport());
    }

    private void buildFormReport(View view) {
        reportForm = new ReportForm(getContext(), true, preferences.getPatternDate());

        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        recycler.addDividerItemDecoration();

        RecyclerHelper.addAll(recycler, reportForm.getHolder());
    }

    private void bindFormWithReport(Report report) {
        reportForm.bindReport(report);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

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
            controller.actionUpdateReport(getEditedReportFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private Report getEditedReportFromForm() throws FormException {
        Report report = scope.getReport();

        report.setDateMeeting(reportForm.getDateMeeting());
        report.setSession(reportForm.getSession());
        report.setComment(reportForm.getComment());

        return report;
    }

    private void deleteReport() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getString(R.string.message_delete),
                getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.actionRemoveReport(scope.getReport());
                    }
                });
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithReport(scope.getReport());
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