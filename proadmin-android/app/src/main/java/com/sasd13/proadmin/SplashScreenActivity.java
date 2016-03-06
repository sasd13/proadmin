package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;

public class SplashScreenActivity extends Activity {

    private static final int TIMEOUT = 3000;

    private TaskPlanner taskPlanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        createLogo();
    }

    private void createLogo() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_app_logo));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Session.init(this);
        Cache.init(this);

        if (Session.containsAttribute(Extra.TEACHER_ID)) {
            goToActivity(HomeActivity.class);
        } else {
            goToActivity(LoginActivity.class);
        }
    }

    private void goToActivity(final Class<?> mClass) {
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, mClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        }, TIMEOUT);

        taskPlanner.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    private void stopGoToActivity() {
        taskPlanner.stop();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }
}