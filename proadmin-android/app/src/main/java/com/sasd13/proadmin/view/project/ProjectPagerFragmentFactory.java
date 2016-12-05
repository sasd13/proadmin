package com.sasd13.proadmin.view.project;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.view.IProjectController;

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
    private Context context;
    private ProjectDetailsFragmentRunnings fragmentRunnings;

    public ProjectPagerFragmentFactory(Fragment fragment, IProjectController controller, Project project) {
        super(fragment.getChildFragmentManager());

        this.controller = controller;
        this.project = project;
        context = fragment.getContext();
    }

    public void setRunnings(List<Running> runnings) {
        if (fragmentRunnings != null && !fragmentRunnings.isDetached()) {
            fragmentRunnings.setRunnings(runnings);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ProjectDetailsFragmentInfos.newInstance(project);
                break;
            case 1:
                fragment = fragmentRunnings = ProjectDetailsFragmentRunnings.newInstance(controller, project);
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
