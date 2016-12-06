package com.sasd13.proadmin.view.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
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

    public void setDependencyWrapper(ReportDependencyWrapper dependencyWrapper) {
        if (fragmentLeadEvaluation != null && !fragmentLeadEvaluation.isDetached()) {
            fragmentLeadEvaluation.setDependencyWrapper(dependencyWrapper);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = fragmentRunningTeams = ReportNewFragmentRunningTeams.newInstance(parentFragment);
                break;
            case 1:
                fragment = ReportNewFragmentInfo.newInstance(controller, parentFragment);
                break;
            case 2:
                fragment = fragmentLeadEvaluation = ReportNewFragmentLeadEvaluation.newInstance(controller, parentFragment);
                break;
            case 3:
                fragment = ReportNewFragmentIndividualEvaluations.newInstance(controller, parentFragment);
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
