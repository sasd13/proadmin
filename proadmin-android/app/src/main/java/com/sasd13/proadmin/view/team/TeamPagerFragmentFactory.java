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

    private Context context;
    private Team team;
    private List<StudentTeam> studentTeams;

    public TeamPagerFragmentFactory(FragmentManager fragmentManager, Context context, Team team, List<StudentTeam> studentTeams) {
        super(fragmentManager);

        this.context = context;
        this.team = team;
        this.studentTeams = studentTeams;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TeamDetailsFragmentInfos.newInstance(team);
            case 1:
                return TeamDetailsFragmentStudents.newInstance(studentTeams);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    @StringRes
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TITLES[position]);
    }
}
