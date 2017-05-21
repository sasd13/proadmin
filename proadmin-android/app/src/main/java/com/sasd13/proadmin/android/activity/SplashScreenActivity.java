package com.sasd13.proadmin.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.android.Configuration;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.Router;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.fragment.SplashScreenFragment;

public class SplashScreenActivity extends AppCompatActivity {

    private Router router;
    private SessionStorage sessionStorage;

    public SessionStorage getSessionStorage() {
        return sessionStorage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
    }

    private void init() {
        router = Configuration.init();
        sessionStorage = new SessionStorage(this);

        startSplashScreenFragment();
    }

    private void startSplashScreenFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, SplashScreenFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.dispatch(mClass, this);
    }

    public void goToMainActivity(final User user) {
        final Intent intent = new Intent(this, MainActivity.class);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY / 2);
    }

    public void goToIdentityActivity() {
        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, IdentityActivity.class));
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY / 2);
    }
}