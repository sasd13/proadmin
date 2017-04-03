package com.sasd13.proadmin.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.controller.ISettingsController;
import com.sasd13.proadmin.controller.ITeamController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_home, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mClass = null;

                switch (v.getId()) {
                    case R.id.home_imageview_project:
                        mClass = IProjectController.class;
                        break;
                    case R.id.home_imageview_team:
                        mClass = ITeamController.class;
                        break;
                    case R.id.home_imageview_report:
                        mClass = IReportController.class;
                        break;
                    case R.id.home_imageview_calendar:
                        mClass = ISettingsController.class;
                        break;
                }

                ((MainActivity) getActivity()).lookup(mClass).entry();
            }
        };

        ImageView imageViewProject = (ImageView) view.findViewById(R.id.home_imageview_project);
        ImageView imageViewTeam = (ImageView) view.findViewById(R.id.home_imageview_team);
        ImageView imageViewReport = (ImageView) view.findViewById(R.id.home_imageview_report);
        ImageView imageViewCalendar = (ImageView) view.findViewById(R.id.home_imageview_calendar);

        imageViewProject.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_project));
        imageViewTeam.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_team));
        imageViewReport.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_report));
        imageViewCalendar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_calendar));

        imageViewCalendar.setOnClickListener(listener);
        imageViewTeam.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_home));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}
