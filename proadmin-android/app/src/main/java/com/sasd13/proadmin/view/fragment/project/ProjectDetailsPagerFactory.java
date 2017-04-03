package com.sasd13.proadmin.view.fragment.project;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ProjectDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_runnings};

    private Context context;

    public ProjectDetailsPagerFactory(Fragment fragment) {
        super(fragment.getChildFragmentManager());

        context = fragment.getContext();
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
        return context.getResources().getString(TITLES[position]);
    }
}
