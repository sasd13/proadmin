package com.sasd13.proadmin.view.report;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportDetailsPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 3;

    @StringRes
    private static final int[] TITLES = {
            R.string.title_information,
            R.string.title_leadevaluation,
            R.string.title_individualevaluations,
    };

    private Context context;
    private Report report;

    public ReportDetailsPagerFragmentFactory(FragmentManager fragmentManager, Context context, Report report) {
        super(fragmentManager);

        this.context = context;
        this.report = report;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportDetailsFragmentInfos.newInstance(report);
            case 1:
                return ReportDetailsFragmentLeadEvaluation.newInstance(report);
            case 2:
                return ReportDetailsFragmentIndividualEvaluations.newInstance(report);
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
