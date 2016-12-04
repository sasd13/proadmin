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

    private IProjectController controller;
    private Project project;
    private List<Running> runnings;
    private Context context;

    public ProjectPagerFragmentFactory(FragmentManager fragmentManager, IProjectController controller, Project project, Context context) {
        super(fragmentManager);

        this.controller = controller;
        this.project = project;
        this.context = context;
    }

    public void setRunnings(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ProjectDetailsFragmentInfos.newInstance(project);
                break;
            case 1:
                fragment = ProjectDetailsFragmentRunnings.newInstance(controller, project);
                ((ProjectDetailsFragmentRunnings) fragment).setRunnings(runnings);
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
