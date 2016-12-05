package com.sasd13.proadmin.view.report;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
import com.sasd13.proadmin.view.IReportController;

import java.util.List;

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

    private IReportController controller;
    private Context context;
    private Report report;

    private ReportDetailsFragmentLeadEvaluation fragmentLeadEvaluation;
    private ReportDetailsFragmentIndividualEvaluations fragmentIndividualEvaluations;

    public ReportDetailsPagerFragmentFactory(FragmentManager fragmentManager, IReportController controller, Context context, Report report) {
        super(fragmentManager);

        this.controller = controller;
        this.context = context;
        this.report = report;
    }

    public void setLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (fragmentLeadEvaluation != null && !fragmentLeadEvaluation.isDetached()) {
            fragmentLeadEvaluation.setLeadEvaluation(leadEvaluation);
        }
    }

    public void setDependencyWrapper(ReportDependencyWrapper dependencyWrapper) {
        if (fragmentLeadEvaluation != null && !fragmentLeadEvaluation.isDetached()) {
            fragmentLeadEvaluation.setDependencyWrapper(dependencyWrapper);
        }
    }

    public void setIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        if (fragmentIndividualEvaluations != null && !fragmentIndividualEvaluations.isDetached()) {
            fragmentIndividualEvaluations.setIndividualEvaluations(individualEvaluations);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ReportDetailsFragmentInfos.newInstance(controller, report);
                break;
            case 1:
                fragment = fragmentLeadEvaluation = ReportDetailsFragmentLeadEvaluation.newInstance(controller, report);
                break;
            case 2:
                fragment = fragmentIndividualEvaluations = ReportDetailsFragmentIndividualEvaluations.newInstance(controller, report);
                break;
        }

        return fragment;
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
