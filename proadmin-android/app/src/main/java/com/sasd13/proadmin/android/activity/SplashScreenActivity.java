package com.sasd13.proadmin.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.proadmin.android.Config;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.Router;
import com.sasd13.proadmin.android.component.IController;
import com.sasd13.proadmin.android.component.splashscreen.view.SplashScreenFragment;

public class SplashScreenActivity extends AppCompatActivity {

    private Router router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
        startSplashScreenFragment();
    }

    private void init() {
        Config config = new Config(this);
        router = config.getRouter();
    }

    private void startSplashScreenFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, SplashScreenFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.navigate(mClass, this);
    }
}