package com.sasd13.proadmin.view.report;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
import com.sasd13.proadmin.view.IReportController;

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

    private IReportController controller;
    private Report report;
    private ReportDependencyWrapper dependencyWrapper;
    private Context context;

    public ReportDetailsPagerFactory(Fragment fragment, IReportController controller, Report report, ReportDependencyWrapper dependencyWrapper) {
        super(fragment.getChildFragmentManager());

        this.controller = controller;
        this.report = report;
        this.dependencyWrapper = dependencyWrapper;
        context = fragment.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportDetailsFragmentInfos.newInstance(controller, report);
            case 1:
                return ReportDetailsFragmentLeadEvaluation.newInstance(controller, report, dependencyWrapper);
            case 2:
                return ReportDetailsFragmentIndividualEvaluations.newInstance(controller, report);
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
