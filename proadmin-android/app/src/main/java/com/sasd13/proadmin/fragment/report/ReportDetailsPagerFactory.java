package com.sasd13.proadmin.fragment.report;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 3;

    @StringRes
    private static final int[] TITLES = {
            R.string.title_information,
            R.string.title_leadevaluation,
            R.string.title_individualevaluations,
    };

    private ReportWrapper reportWrapper;
    private Context context;

    public ReportDetailsPagerFactory(Fragment fragment, ReportWrapper reportWrapper) {
        super(fragment.getChildFragmentManager());

        this.reportWrapper = reportWrapper;
        context = fragment.getContext();
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
        return context.getResources().getString(TITLES[position]);
    }
}
