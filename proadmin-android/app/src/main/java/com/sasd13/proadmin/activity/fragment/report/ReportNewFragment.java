package com.sasd13.proadmin.activity.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.ReportsActivity;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class ReportNewFragment extends Fragment {

    private ReportsActivity parentActivity;

    private Pager pager;
    private ReportNewPagerFragmentFactory pagerFragmentFactory;

    private RunningTeam runningTeam;

    public static ReportNewFragment newInstance() {
        return new ReportNewFragment();
    }

    public static ReportNewFragment newInstance(RunningTeam runningTeam) {
        ReportNewFragment fragment = newInstance();
        fragment.setRunningTeam(runningTeam);

        return fragment;
    }

    public void setRunningTeam(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;

        if (pagerFragmentFactory != null) {
            pagerFragmentFactory.setRunningTeam(runningTeam);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ReportsActivity) getActivity();
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
        pagerFragmentFactory = new ReportNewPagerFragmentFactory(getChildFragmentManager(), getContext(), this);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.layout_vp_viewpager);
        pager = new Pager(viewPager, pagerFragmentFactory);

        parentActivity.setPager(pager);

        if (runningTeam != null) {
            pagerFragmentFactory.setRunningTeam(runningTeam);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_report));
    }

    public void setCurrentItemSubtitle() {
        parentActivity.getSupportActionBar().setSubtitle(pagerFragmentFactory.getSubtitle(pager.getCurrentItem()));
    }

    public void backward() {
        pager.backward();
    }

    public void forward() {
        pager.forward();
    }
}