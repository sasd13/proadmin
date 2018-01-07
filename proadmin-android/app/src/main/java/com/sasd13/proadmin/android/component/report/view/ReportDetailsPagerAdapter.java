package com.sasd13.proadmin.android.component.report.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.android.R;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportDetailsPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 3;

    private final String[] TITLES;

    public ReportDetailsPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());

        TITLES = new String[]{
                fragment.getContext().getString(R.string.title_information),
                fragment.getContext().getString(R.string.title_leadevaluation),
                fragment.getContext().getString(R.string.title_individualevaluations)
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportDetailsFragmentInfos.newInstance();
            case 1:
                return ReportDetailsFragmentLeadEvaluation.newInstance();
            case 2:
                return ReportDetailsFragmentIndividualEvaluations.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
