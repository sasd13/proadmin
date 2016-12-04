package com.sasd13.proadmin.view.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.ProjectsActivity;

import java.util.List;

public class ProjectDetailsFragment extends Fragment {

    private Project project;
    private List<Running> runnings;

    public static ProjectDetailsFragment newInstance(Project project, List<Running> runnings) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        fragment.project = project;
        fragment.runnings = runnings;

        return fragment;
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
        Pager pager = (Pager) view.findViewById(R.id.layout_vp_w_psts_viewpager);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.layout_vp_w_psts_pagerslidingtabstrip);

        pager.setAdapter(new ProjectPagerFragmentFactory(getChildFragmentManager(), getContext(), project, runnings));
        tabsStrip.setViewPager(pager);
        ((ProjectsActivity) getActivity()).setPager(pager);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((ProjectsActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_project));
        ((ProjectsActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((ProjectsActivity) getActivity()).setPager(null);
    }
}