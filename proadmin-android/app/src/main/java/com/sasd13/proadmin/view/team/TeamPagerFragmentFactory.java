package com.sasd13.proadmin.view.team;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class TeamPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_students};

    private ITeamController controller;
    private Context context;
    private Team team;
    private TeamDetailsFragmentStudents fragmentStudents;

    public TeamPagerFragmentFactory(FragmentManager fragmentManager, ITeamController controller, Context context, Team team) {
        super(fragmentManager);

        this.controller = controller;
        this.context = context;
        this.team = team;
    }

    public void setStudentTeams(List<StudentTeam> studentTeams) {
        if (fragmentStudents != null && !fragmentStudents.isDetached()) {
            fragmentStudents.setStudentTeams(studentTeams);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = TeamDetailsFragmentInfos.newInstance(controller, team);
                break;
            case 1:
                fragment = fragmentStudents = TeamDetailsFragmentStudents.newInstance(controller, team);
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
