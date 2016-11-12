package com.sasd13.proadmin.activity.fragment.report;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportPagerFragmentFactory implements IPagerFragmentFactory {

    private static final int COUNT = 3;
    private static final @StringRes int[] TITLES = {
            R.string.title_information,
            R.string.title_leadevaluation,
            R.string.title_individualevaluations,
    };

    private Report report;

    public ReportPagerFragmentFactory(Report report) {
        this.report = report;
    }

    @Override
    public Fragment make(int position) {
        switch (position) {
            case 0:
                return ReportDetailsFragmentInfos.newInstance(report);
            case 1:
                return ReportDetailsFragmentLeadEvaluation.newInstance(report);
            case 2:
                return ReportDetailsFragmentIndividualEvaluation.newInstance(report);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public
    @StringRes
    int getPageTitle(int position) {
        return TITLES[position];
    }
}
