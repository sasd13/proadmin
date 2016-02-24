package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sasd13.proadmin.constant.Extra;

public class HomeActivity extends MotherActivity {

    private ImageView imageViewProject, imageViewTeam, imageViewReport, imageViewCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        createHomeMenus();
    }

    private void createHomeMenus() {
        imageViewProject = (ImageView) findViewById(R.id.home_imageview_project);
        imageViewTeam = (ImageView) findViewById(R.id.home_imageview_team);
        imageViewReport = (ImageView) findViewById(R.id.home_imageview_report);
        imageViewCalendar = (ImageView) findViewById(R.id.home_imageview_calendar);

        addDrawablesToImageViews();
        addListenersToImageViews();
    }

    private void addDrawablesToImageViews() {
        imageViewCalendar.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_calendar));
        imageViewProject.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_project));
        imageViewTeam.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_team));
        imageViewReport.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.imageview_report));
    }

    private void addListenersToImageViews() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (v.getId()) {
                    case R.id.home_imageview_project:
                        intent = new Intent(HomeActivity.this, ProjectsActivity.class);
                        break;
                    /*case R.id.home_imageview_team:
                        intent = new Intent(HomeActivity. TeamsActivity.class);
                        break;*/
                    /*case R.id.home_imageview_report:
                        intent = new Intent(HomeActivity. ReportsActivity.class);
                        break;*/
                    case R.id.home_imageview_calendar:
                        intent = new Intent(HomeActivity.this, SettingActivity.class);
                        break;
                }

                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
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

    private void exit() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_action_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}