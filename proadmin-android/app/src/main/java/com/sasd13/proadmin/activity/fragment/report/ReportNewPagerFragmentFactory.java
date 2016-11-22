package com.sasd13.proadmin.activity.fragment.report;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;

    @StringRes
    private static final int[] SUBTITLES = {
            R.string.title_runningteam,
            R.string.title_information,
            R.string.title_leadevaluation,
            R.string.title_individualevaluations
    };

    private Context context;
    private ReportNewFragment parentFragment;
    private RunningTeam runningTeam;

    public ReportNewPagerFragmentFactory(FragmentManager fragmentManager, Context context, ReportNewFragment parentFragment) {
        super(fragmentManager);

        this.context = context;
        this.parentFragment = parentFragment;
    }

    public void setRunningTeam(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportNewFragmentRunningTeams.newInstance(parentFragment, runningTeam);
            case 1:
                return ReportNewFragmentInfo.newInstance(parentFragment, runningTeam);
            case 2:
                //return ReportNewFragmentLeadEvaluation.newInstance();
            case 3:
                //return ReportNewFragmentIndividualEvaluations.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    public CharSequence getSubtitle(int position) {
        return context.getResources().getString(SUBTITLES[position]);
    }
}
