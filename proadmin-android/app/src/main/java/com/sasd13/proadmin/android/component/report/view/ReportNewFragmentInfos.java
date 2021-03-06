package com.sasd13.proadmin.android.component.report.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.sasd13.proadmin.android.component.report.scope.ReportScope;
import com.sasd13.proadmin.android.gui.form.ReportForm;
import com.sasd13.proadmin.android.model.Report;

import java.util.Observable;
import java.util.Observer;

public class ReportNewFragmentInfos extends Fragment implements Observer {

    private IReportController controller;
    private ReportScope scope;
    private ReportForm reportForm;

    public static ReportNewFragmentInfos newInstance() {
        return new ReportNewFragmentInfos();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
        scope = (ReportScope) controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv_w_fab, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormReport(view);
        buildFloatingActionButton(view);
        bindFormWithReport(scope.getReport());
    }

    private void buildFormReport(View view) {
        reportForm = new ReportForm(getContext(), false, scope.getUserPreferences());
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, reportForm.getHolder());
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_save_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReport();
            }
        });
    }

    private void createReport() {
        try {
            controller.actionCreateReport(getEditedReportWithForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private Report getEditedReportWithForm() throws FormException {
        Report report = scope.getReport();

        report.setDateMeeting(reportForm.getDateMeeting());
        report.setSession(reportForm.getSession());
        report.setComment(reportForm.getComment());

        return report;
    }

    private void bindFormWithReport(Report report) {
        reportForm.bindReport(report);
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithReport(scope.getReport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}