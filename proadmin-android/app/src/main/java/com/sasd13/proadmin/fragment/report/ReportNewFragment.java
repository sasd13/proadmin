package com.sasd13.proadmin.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;

public class ReportNewFragment extends Fragment implements IPagerHandler {

    private IReportController controller;
    private ReportWrapper reportWrapper;
    private Pager pager;

    public static ReportNewFragment newInstance(ReportWrapper reportWrapper) {
        ReportNewFragment fragment = new ReportNewFragment();
        fragment.reportWrapper = reportWrapper;

        return fragment;
    }

    @Override
    public boolean handleBackPress() {
        return pager.handleBackPress(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (IReportController) ((MainActivity) getActivity()).lookup(IReportController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_vp, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildPager(view);
    }

    private void buildPager(View view) {
        pager = (Pager) view.findViewById(R.id.layout_vp_viewpager);

        pager.setAdapter(new ReportNewPagerFactory(this, reportWrapper));
        pager.setScrollable(false);
        ((MainActivity) getActivity()).setPagerHandler(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getResources().getString(R.string.title_new));
    }

    public void forward() {
        pager.forward();
    }

    public void createReport() {
        controller.createReport(reportWrapper.getReport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPagerHandler(null);
    }
}