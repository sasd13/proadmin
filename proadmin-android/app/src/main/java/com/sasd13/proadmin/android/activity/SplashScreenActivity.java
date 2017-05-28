package com.sasd13.proadmin.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.proadmin.android.Configurator;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.Router;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.fragment.SplashScreenFragment;

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
        Configurator.Config config = Configurator.init(this);
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