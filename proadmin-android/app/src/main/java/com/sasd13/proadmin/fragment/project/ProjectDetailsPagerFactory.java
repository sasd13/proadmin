package com.sasd13.proadmin.fragment.project;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.wrapper.ProjectWrapper;
import com.sasd13.proadmin.fragment.IProjectController;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private IProjectController controller;
    private ProjectWrapper projectWrapper;
    private Context context;

    public ProjectDetailsPagerFactory(Fragment fragment, IProjectController controller, ProjectWrapper projectWrapper) {
        super(fragment.getChildFragmentManager());

        this.controller = controller;
        this.projectWrapper = projectWrapper;
        context = fragment.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProjectDetailsFragmentInfos.newInstance(projectWrapper);
            case 1:
                return ProjectDetailsFragmentRunnings.newInstance(controller, projectWrapper);
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
