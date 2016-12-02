package com.sasd13.proadmin.activity.fragment.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;

    private ReportNewFragment parentFragment;

    public ReportNewPagerFragmentFactory(FragmentManager fragmentManager, ReportNewFragment parentFragment) {
        super(fragmentManager);

        this.parentFragment = parentFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ReportNewFragmentRunningTeams.newInstance(parentFragment);
                break;
            case 1:
                fragment = ReportNewFragmentInfo.newInstance(parentFragment);
                break;
            case 2:
                fragment = ReportNewFragmentLeadEvaluation.newInstance(parentFragment);
                break;
            case 3:
                fragment = ReportNewFragmentIndividualEvaluations.newInstance(parentFragment);
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
