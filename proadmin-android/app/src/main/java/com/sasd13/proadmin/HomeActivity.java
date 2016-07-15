package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.content.Extra;

public class HomeActivity extends MotherActivity {

    public static HomeActivity self;

    private ImageView imageViewProject, imageViewTeam, imageViewReport, imageViewCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        self = this;

        buildHomeView();
    }

    private void buildHomeView() {
        imageViewProject = (ImageView) findViewById(R.id.home_imageview_project);
        imageViewTeam = (ImageView) findViewById(R.id.home_imageview_team);
        imageViewReport = (ImageView) findViewById(R.id.home_imageview_report);
        imageViewCalendar = (ImageView) findViewById(R.id.home_imageview_calendar);

        addDrawablesToNavItems();
        addListenersToNavItems();
    }

    private void addDrawablesToNavItems() {
        imageViewCalendar.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_calendar));
        imageViewProject.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_project));
        imageViewTeam.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_team));
        imageViewReport.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_report));
    }

    private void addListenersToNavItems() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mClass = null;

                switch (v.getId()) {
                    case R.id.home_imageview_project:
                        mClass = ProjectsActivity.class;
                        break;
                    /*case R.id.home_imageview_team:
                        mClass = TeamsActivity.class;
                        break;*/
                    /*case R.id.home_imageview_report:
                        mClass = ReportsActivity.class;
                        break;*/
                    case R.id.home_imageview_calendar:
                        mClass = SettingActivity.class;
                        break;
                }

                startActivity(new Intent(HomeActivity.this, mClass));
            }
        };

        imageViewCalendar.setOnClickListener(listener);
        imageViewTeam.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);
            exit();
        }
    }

    public void exit() {
        final WaitDialog waitDialog = new WaitDialog(this);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(HomeActivity.this, LogInActivity.class));
                waitDialog.dismiss();
                finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();

        waitDialog.show();
    }
}