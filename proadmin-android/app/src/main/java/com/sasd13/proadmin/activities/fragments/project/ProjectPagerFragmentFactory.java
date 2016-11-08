package com.sasd13.proadmin.activities.fragments.project;

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
    private static final @StringRes int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private Project project;

    public ProjectPagerFragmentFactory(Project project) {
        this.project = project;
    }

    @Override
    public Fragment make(int position) {
        switch (position) {
            case 0:
                return ProjectPagerFragmentInfos.newInstance(project);
            case 1:
                return ProjectPagerFragmentRunnings.newInstance(project);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public @StringRes int getPageTitle(int position) {
        return TITLES[position];
    }
}
