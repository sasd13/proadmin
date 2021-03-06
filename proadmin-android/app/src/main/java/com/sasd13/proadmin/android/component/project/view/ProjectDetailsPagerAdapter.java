package com.sasd13.proadmin.android.component.project.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.android.R;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectDetailsPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private final String[] TITLES;

    public ProjectDetailsPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());

        TITLES = new String[]{
                fragment.getContext().getString(R.string.title_information),
                fragment.getContext().getString(R.string.title_runnings)
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProjectDetailsFragmentInfos.newInstance();
            case 1:
                return ProjectDetailsFragmentRunnings.newInstance();
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
        return TITLES[position];
    }
}
