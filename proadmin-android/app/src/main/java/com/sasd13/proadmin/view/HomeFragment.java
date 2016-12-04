package com.sasd13.proadmin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.settings.SettingsController;
import com.sasd13.proadmin.controller.team.TeamController;
import com.sasd13.proadmin.util.SessionHelper;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class HomeFragment extends Fragment {

    private ImageView imageViewProject, imageViewTeam, imageViewReport, imageViewCalendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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
        imageViewProject = (ImageView) view.findViewById(R.id.home_imageview_project);
        imageViewTeam = (ImageView) view.findViewById(R.id.home_imageview_team);
        imageViewReport = (ImageView) view.findViewById(R.id.home_imageview_report);
        imageViewCalendar = (ImageView) view.findViewById(R.id.home_imageview_calendar);

        setImageDrawables();
        setImagesListeners();
    }

    private void setImageDrawables() {
        imageViewCalendar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_calendar));
        imageViewProject.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_project));
        imageViewTeam.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_team));
        imageViewReport.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.imageview_report));
    }

    private void setImagesListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mClass = null;

                switch (v.getId()) {
                    case R.id.home_imageview_project:
                        mClass = ProjectController.class;
                        break;
                    case R.id.home_imageview_team:
                        mClass = TeamController.class;
                        break;
                    case R.id.home_imageview_report:
                        mClass = ReportController.class;
                        break;
                    case R.id.home_imageview_calendar:
                        mClass = SettingsController.class;
                        break;
                }

                startActivity(new Intent(getContext(), mClass));
            }
        };

        imageViewCalendar.setOnClickListener(listener);
        imageViewTeam.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_action_logout:
                SessionHelper.logOut(getActivity());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
