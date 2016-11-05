package com.sasd13.proadmin.activities.fragments.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectFragment extends Fragment {

    private Project project;
    private ProjectsActivity parentActivity;
    private IPagerFragmentFactory pagerFragmentFactory;

    public static ProjectFragment newInstance(Project project) {
        ProjectFragment projectFragment = new ProjectFragment();
        projectFragment.project = project;

        return projectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ProjectsActivity) getActivity();
        pagerFragmentFactory = new ProjectPagerFragmentFactory(project);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_project, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildViewPager(view);
    }

    private void buildViewPager(View view) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.project_viewpager);
        Pager pager = new Pager(viewPager, getChildFragmentManager(), pagerFragmentFactory);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.project_tabstrip);

        tabsStrip.setViewPager(viewPager);
        parentActivity.setPagerHandler(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_project));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        parentActivity.setPagerHandler(null);
    }
}