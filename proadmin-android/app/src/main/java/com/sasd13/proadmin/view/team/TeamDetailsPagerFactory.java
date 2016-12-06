package com.sasd13.proadmin.view.team;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.TeamDependencyWrapper;
import com.sasd13.proadmin.view.ITeamController;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class TeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_students};

    private ITeamController controller;
    private Team team;
    private TeamDependencyWrapper dependencyWrapper;
    private Context context;

    public TeamDetailsPagerFactory(Fragment fragment, ITeamController controller, Team team, TeamDependencyWrapper dependencyWrapper) {
        super(fragment.getChildFragmentManager());

        this.controller = controller;
        this.team = team;
        this.dependencyWrapper = dependencyWrapper;
        context = fragment.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TeamDetailsFragmentInfos.newInstance(controller, team);
            case 1:
                return TeamDetailsFragmentStudents.newInstance(controller, team, dependencyWrapper);
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
