package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskInitializer;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.SessionHelper;

public class SplashScreenActivity extends AppCompatActivity implements TaskInitializer.Loader {

    private static final int TIMEOUT = 2 * GUIConstants.TIMEOUT_ACTIVITY;

    private TaskPlanner taskPlanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        init();
    }

    private void init() {
        new TaskInitializer(this).execute();

        createLogo();
    }

    private void createLogo() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_app_logo));
    }

    @Override
    public void load() {
        Session.init(this);
        Cache.init(this);
    }

    @Override
    public void run() {
        if (SessionHelper.isLogged()) {
            goToActivity(HomeActivity.class);
        } else {
            goToActivity(LogInActivity.class);
        }
    }

    private void goToActivity(final Class<? extends Activity> mClass) {
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, mClass));
            }
        }, TIMEOUT).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    private void stopGoToActivity() {
        if (taskPlanner != null) {
            taskPlanner.stop();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }
}