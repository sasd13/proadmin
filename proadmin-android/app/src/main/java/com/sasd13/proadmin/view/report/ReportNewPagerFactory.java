package com.sasd13.proadmin.view.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.view.IReportController;

import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;

    private IReportController controller;
    private ReportNewFragment parentFragment;
    private ReportNewFragmentRunningTeams fragmentRunningTeams;
    private ReportNewFragmentLeadEvaluation fragmentLeadEvaluation;
    private ReportNewFragmentIndividualEvaluations fragmentIndividualEvaluations;

    public ReportNewPagerFactory(ReportNewFragment parentFragment, IReportController controller) {
        super(parentFragment.getChildFragmentManager());

        this.controller = controller;
        this.parentFragment = parentFragment;
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        if (fragmentRunningTeams != null && !fragmentRunningTeams.isDetached()) {
            fragmentRunningTeams.setRunningTeams(runningTeams);
        }
    }

    public void setReportWrapper(ReportWrapper reportWrapper) {
        if (fragmentLeadEvaluation != null && !fragmentLeadEvaluation.isDetached()) {
            fragmentLeadEvaluation.setReportWrapper(reportWrapper);
        }

        if (fragmentIndividualEvaluations != null && !fragmentIndividualEvaluations.isDetached()) {
            fragmentIndividualEvaluations.setReportWrapper(reportWrapper);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = fragmentRunningTeams = ReportNewFragmentRunningTeams.newInstance(controller, parentFragment);
                break;
            case 1:
                fragment = ReportNewFragmentInfo.newInstance(controller, parentFragment);
                break;
            case 2:
                fragment = fragmentLeadEvaluation = ReportNewFragmentLeadEvaluation.newInstance(controller, parentFragment);
                break;
            case 3:
                fragment = fragmentIndividualEvaluations = ReportNewFragmentIndividualEvaluations.newInstance(controller, parentFragment);
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
