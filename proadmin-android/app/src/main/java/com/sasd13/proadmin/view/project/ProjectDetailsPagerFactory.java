package com.sasd13.proadmin.view.project;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.ProjectDependencyWrapper;
import com.sasd13.proadmin.view.IProjectController;

import java.util.Observer;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private IProjectController controller;
    private Project project;
    private ProjectDependencyWrapper dependencyWrapper;
    private Context context;

    public ProjectDetailsPagerFactory(Fragment fragment, IProjectController controller, Project project, ProjectDependencyWrapper dependencyWrapper) {
        super(fragment.getChildFragmentManager());

        this.controller = controller;
        this.project = project;
        this.dependencyWrapper = dependencyWrapper;
        context = fragment.getContext();
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
                dependencyWrapper.addObserver((Observer) fragment);
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
