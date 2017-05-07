package com.sasd13.proadmin.android.view.fragment.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;

public class ReportNewFragment extends Fragment implements IPagerHandler {

    private Pager pager;

    public static ReportNewFragment newInstance() {
        return new ReportNewFragment();
    }

    @Override
    public boolean handleBackPress() {
        return pager.handleBackPress(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_vp, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildPager(view);
    }

    private void buildPager(View view) {
        pager = (Pager) view.findViewById(R.id.layout_vp_viewpager);

        pager.setAdapter(new ReportNewPagerAdapter(this));
        pager.setScrollable(false);
        ((MainActivity) getActivity()).setPagerHandler(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_report));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.title_new));
    }

    public void forward() {
        pager.forward();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((MainActivity) getActivity()).setPagerHandler(null);
    }
}