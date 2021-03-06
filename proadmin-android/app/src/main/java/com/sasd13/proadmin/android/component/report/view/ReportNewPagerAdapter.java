package com.sasd13.proadmin.android.component.report.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private ReportNewFragment parentFragment;

    public ReportNewPagerAdapter(ReportNewFragment parentFragment) {
        super(parentFragment.getChildFragmentManager());

        this.parentFragment = parentFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportNewFragmentRunningTeams.newInstance(parentFragment);
            case 1:
                return ReportNewFragmentInfos.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
