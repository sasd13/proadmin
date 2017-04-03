package com.sasd13.proadmin.view.fragment.report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.scope.ReportWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 3;

    private final String[] TITLES;

    private ReportWrapper reportWrapper;

    public ReportDetailsPagerFactory(Fragment fragment, ReportWrapper reportWrapper) {
        super(fragment.getChildFragmentManager());

        this.reportWrapper = reportWrapper;
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
                return ReportDetailsFragmentInfos.newInstance(reportWrapper);
            case 1:
                return ReportDetailsFragmentLeadEvaluation.newInstance(reportWrapper);
            case 2:
                return ReportDetailsFragmentIndividualEvaluations.newInstance(reportWrapper);
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
