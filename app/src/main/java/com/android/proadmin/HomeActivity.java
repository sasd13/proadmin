package com.android.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.proadmin.R;

import proadmin.constant.Extra;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;

public class HomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ImageView imageViewSquad = (ImageView) findViewById(R.id.fen_squad);
        ImageView imageViewProject = (ImageView) findViewById(R.id.fen_projet);
        ImageView imageViewReport = (ImageView) findViewById(R.id.fen_report);
        ImageView imageViewCalendar = (ImageView) findViewById(R.id.fen_calendar);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                switch (v.getId()) {
                    case R.id.fen_squad:
                        intent = new Intent(HomeActivity.this, SquadsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.fen_projet:
                        intent = new Intent(HomeActivity.this, ProjectsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.fen_report:
                        intent = new Intent(HomeActivity.this, ReportsActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        imageViewSquad.setOnClickListener(listener);
        imageViewProject.setOnClickListener(listener);
        imageViewReport.setOnClickListener(listener);
        imageViewCalendar.setOnClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.WELCOME) && getIntent().getBooleanExtra(Extra.WELCOME, false)) {
            getIntent().removeExtra(Extra.WELCOME);

            CharSequence firstName = getIntent().getCharSequenceExtra(Extra.TEACHER_FIRSTNAME);
            CustomDialog.showDialog(
                    this,
                    getResources().getString(R.string.home_alertdialog_welcome_title),
                    getResources().getString(R.string.home_alertdialog_welcome_message) + " " + firstName + " !",
                    CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                    null);
        } else if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);

            finish();
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
}