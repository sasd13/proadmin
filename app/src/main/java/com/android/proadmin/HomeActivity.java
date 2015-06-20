package com.android.proadmin;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import proadmin.constant.Extra;
import proadmin.gui.widget.dialog.CustomDialog;
import proadmin.gui.widget.dialog.CustomDialogBuilder;

public class HomeActivity extends Activity {

    private static final int LOGOUT_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

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
                        intent = new Intent(HomeActivity.this, SettingsActivity.class);
                        break;
                    case R.id.imageview_project:
                        intent = new Intent(HomeActivity.this, ProjectsActivity.class);
                        break;
                    case R.id.imageview_squad:
                        intent = new Intent(HomeActivity.this, SquadsActivity.class);
                        break;
                    case R.id.imageview_report:
                        intent = new Intent(HomeActivity.this, ReportsActivity.class);
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
        imageViewSquad.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.WELCOME) && getIntent().getBooleanExtra(Extra.WELCOME, false)) {
            getIntent().removeExtra(Extra.WELCOME);

            showWelcome();
        } else if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);

            goToLoginActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void showWelcome() {
        String firstName = getIntent().getStringExtra(Extra.TEACHER_FIRSTNAME);
        CustomDialog.showOkDialog(
                this,
                getResources().getString(R.string.home_alertdialog_welcome_title),
                getResources().getString(R.string.home_alertdialog_welcome_message) + " " + firstName + " !");
    }

    private void goToLoginActivity() {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOGOUT_TIME_OUT);

        dialog.show();
    }
}