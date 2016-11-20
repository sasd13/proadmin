package com.sasd13.proadmin.activity.fragment.project;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectPagerFragmentFactory implements IPagerFragmentFactory {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private Project project;

    public ProjectPagerFragmentFactory(Project project) {
        this.project = project;
    }

    @Override
    public Fragment make(int position) {
        switch (position) {
            case 0:
                return ProjectDetailsFragmentInfos.newInstance(project);
            case 1:
                return ProjectDetailsFragmentRunnings.newInstance(project);
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
    public int getPageTitle(int position) {
        return TITLES[position];
    }
}
