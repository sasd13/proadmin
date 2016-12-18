package com.sasd13.proadmin.fragment.report;

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

public class ReportNewFragmentInfo extends Fragment {

    private IReportController controller;
    private Report report;
    private ReportNewFragment parentFragment;
    private ReportForm reportForm;

    public static ReportNewFragmentInfo newInstance(ReportWrapper reportWrapper, ReportNewFragment parentFragment) {
        ReportNewFragmentInfo fragment = new ReportNewFragmentInfo();
        fragment.report = reportWrapper.getReport();
        fragment.parentFragment = parentFragment;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
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
        buildFormRunning(view);
        buildFloatingActionButton(view);
        bindFormWithReport();
    }

    private void buildFormRunning(View view) {
        reportForm = new ReportForm(getContext(), false);

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_w_fab_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, reportForm.getHolder());
    }

    private void buildFloatingActionButton(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.layout_rv_w_fab_floatingactionbutton);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_navigate_next_white_48dp));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editReportWithForm();
                    goForward();
                } catch (FormException e) {
                    controller.displayMessage(e.getMessage());
                }
            }
        });
    }

    private void editReportWithForm() throws FormException {
        report.setDateMeeting(reportForm.getDateMeeting());
        report.setSession(reportForm.getSession());
        report.setComment(reportForm.getComment());
    }

    private void bindFormWithReport() {
        reportForm.bindReport(report);
    }

    private void goForward() {
        parentFragment.forward();
    }
}