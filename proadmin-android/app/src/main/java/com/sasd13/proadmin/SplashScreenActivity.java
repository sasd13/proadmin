package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.sasd13.proadmin.db.SQLiteDAO;
import com.sasd13.proadmin.session.Session;

public class SplashScreenActivity extends Activity {

    private static final int SPLASHSCREEN_TIMEOUT = 3000;

    private Handler handler;
    private Runnable runnable;

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

        SQLiteDAO.getInstance().init(this);
        Session.start(this);

        if (Session.isStarted()) {
            goToActivity(HomeActivity.class, SPLASHSCREEN_TIMEOUT);
        } else {
            goToActivity(LogActivity.class, SPLASHSCREEN_TIMEOUT);
        }
    }

    private void goToActivity(Class<?> activityClass, int timeOut) {
        final Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        this.runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, timeOut);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    private void stopGoToActivity() {
        try {
            this.handler.removeCallbacks(this.runnable);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }
}