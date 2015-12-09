package com.sasd13.proadmin;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
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
        this.imageViewProject = (ImageView) findViewById(R.id.imageview_project);
        this.imageViewTeam = (ImageView) findViewById(R.id.imageview_team);
        this.imageViewReport = (ImageView) findViewById(R.id.imageview_report);
        this.imageViewCalendar = (ImageView) findViewById(R.id.imageview_calendar);

        addDrawableToImageViews();
        addListenerToImageViews();
    }

    private void addDrawableToImageViews() {
        Resources resources = getResources();

        this.imageViewCalendar.setImageDrawable(resources.getDrawable(R.drawable.imageview_calendar));
        this.imageViewProject.setImageDrawable(resources.getDrawable(R.drawable.imageview_project));
        this.imageViewTeam.setImageDrawable(resources.getDrawable(R.drawable.imageview_team));
        this.imageViewReport.setImageDrawable(resources.getDrawable(R.drawable.imageview_report));
    }

    private void addListenerToImageViews() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (v.getId()) {
                    case R.id.imageview_calendar:
                        intent = new Intent(HomeActivity.this, SettingActivity.class);
                        break;
                    case R.id.imageview_project:
                        intent = new Intent(HomeActivity.this, ProjectsActivity.class);
                        break;
                    /*case R.id.imageview_team:
                        intent = new Intent(HomeActivity.this, TeamsActivity.class);
                        break;*/
                    /*case R.id.imageview_report:
                        intent = new Intent(HomeActivity.this, ReportsActivity.class);
                        break;*/
                }

                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };

        this.imageViewCalendar.setOnClickListener(listener);
        this.imageViewTeam.setOnClickListener(listener);
        this.imageViewProject.setOnClickListener(listener);
        this.imageViewReport.setOnClickListener(listener);
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
        Intent intent = new Intent(this, LogActivity.class);
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