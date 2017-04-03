package com.sasd13.proadmin.view.fragment.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.util.wrapper.ReportWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private ReportNewFragment parentFragment;
    private ReportWrapper reportWrapper;

    public ReportNewPagerFactory(ReportNewFragment parentFragment, ReportWrapper reportWrapper) {
        super(parentFragment.getChildFragmentManager());

        this.parentFragment = parentFragment;
        this.reportWrapper = reportWrapper;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportNewFragmentRunningTeams.newInstance(reportWrapper, parentFragment);
            case 1:
                return ReportNewFragmentInfo.newInstance(reportWrapper, parentFragment);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
