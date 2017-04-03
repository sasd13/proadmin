package com.sasd13.proadmin.view.fragment.team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.scope.TeamWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class TeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private final String[] TITLES;

    private TeamWrapper teamWrapper;

    public TeamDetailsPagerFactory(Fragment fragment, TeamWrapper teamWrapper) {
        super(fragment.getChildFragmentManager());

        this.teamWrapper = teamWrapper;

        TITLES = new String[]{
                fragment.getString(R.string.title_information),
                fragment.getString(R.string.title_students)
        };
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
        return TITLES[position];
    }
}
