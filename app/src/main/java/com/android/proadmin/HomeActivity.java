package com.android.proadmin;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import proadmin.constant.Extra;

public class HomeActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        createHomeMenus();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);

            exit();
        }
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

    private void createHomeMenus() {
        ImageView imageViewCalendar = (ImageView) findViewById(R.id.imageview_calendar);
        ImageView imageViewProject = (ImageView) findViewById(R.id.imageview_project);
        ImageView imageViewSquad = (ImageView) findViewById(R.id.imageview_squad);
        ImageView imageViewReport = (ImageView) findViewById(R.id.imageview_report);

        Resources resources = getResources();

        imageViewCalendar.setImageDrawable(resources.getDrawable(R.drawable.imageview_calendar));
        imageViewProject.setImageDrawable(resources.getDrawable(R.drawable.imageview_project));
        imageViewSquad.setImageDrawable(resources.getDrawable(R.drawable.imageview_squad));
        imageViewReport.setImageDrawable(resources.getDrawable(R.drawable.imageview_report));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (v.getId()) {
                    case R.id.imageview_calendar:
                        intent = new Intent(HomeActivity.this, SettingActivity.class);
                        break;
                    /*case R.id.imageview_project:
                        intent = new Intent(HomeActivity.this, ProjectsActivity.class);
                        break;
                    case R.id.imageview_squad:
                        intent = new Intent(HomeActivity.this, SquadsActivity.class);
                        break;
                    case R.id.imageview_report:
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

        imageViewCalendar.setOnClickListener(listener);
        imageViewSquad.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
    }

    private void exit() {
        Intent intent = new Intent(this, LogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }
}