package com.sasd13.proadmin.view.project;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private Context context;
    private Project project;
    private List<Running> runnings;

    public ProjectPagerFragmentFactory(FragmentManager fragmentManager, Context context, Project project, List<Running> runnings) {
        super(fragmentManager);

        this.context = context;
        this.project = project;
        this.runnings = runnings;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProjectDetailsFragmentInfos.newInstance(project);
            case 1:
                return ProjectDetailsFragmentRunnings.newInstance(runnings);
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
