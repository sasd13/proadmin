package com.sasd13.proadmin.view.fragment.team;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.wrapper.TeamWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class TeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_students};

    private TeamWrapper teamWrapper;
    private Context context;

    public TeamDetailsPagerFactory(Fragment fragment, TeamWrapper teamWrapper) {
        super(fragment.getChildFragmentManager());

        this.teamWrapper = teamWrapper;
        context = fragment.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TeamDetailsFragmentInfos.newInstance(teamWrapper);
            case 1:
                return TeamDetailsFragmentStudents.newInstance(teamWrapper);
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
