package com.sasd13.proadmin.fragment.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;

    private ReportNewFragment parentFragment;

    public ReportNewPagerFactory(ReportNewFragment parentFragment) {
        super(parentFragment.getChildFragmentManager());

        this.parentFragment = parentFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportNewFragmentRunningTeams.newInstance(parentFragment);
            case 1:
                return ReportNewFragmentInfo.newInstance(parentFragment);
            case 2:
                return ReportNewFragmentLeadEvaluation.newInstance(parentFragment);
            case 3:
                return ReportNewFragmentIndividualEvaluations.newInstance(parentFragment);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
