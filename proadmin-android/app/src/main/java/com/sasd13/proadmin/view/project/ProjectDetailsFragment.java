package com.sasd13.proadmin.view.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.ProjectDependencyWrapper;
import com.sasd13.proadmin.view.IProjectController;

public class ProjectDetailsFragment extends Fragment implements IPagerHandler {

    private IProjectController controller;
    private Project project;
    private ProjectDependencyWrapper dependencyWrapper;
    private Pager pager;

    public static ProjectDetailsFragment newInstance(IProjectController controller, Project project, ProjectDependencyWrapper dependencyWrapper) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        fragment.controller = controller;
        fragment.project = project;
        fragment.dependencyWrapper = dependencyWrapper;

        return fragment;
    }

    @Override
    public boolean handleBackPress() {
        return pager.handleBackPress(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_vp_w_psts, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildPager(view);
    }

    private void buildPager(View view) {
        pager = (Pager) view.findViewById(R.id.layout_vp_w_psts_viewpager);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(new ProjectDetailsPagerFactory(this, controller, project, dependencyWrapper));
        tabsStrip.setViewPager(pager);
        ((MainActivity) getActivity()).setPagerHandler(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_project));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPagerHandler(null);


    }
}