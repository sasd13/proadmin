package com.sasd13.proadmin.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.proadmin.android.Config;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.Router;
import com.sasd13.proadmin.android.component.IController;
import com.sasd13.proadmin.android.component.authentication.view.LogInFragment;

public class IdentityActivity extends AppCompatActivity {

    private Router router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
        startLogInFragment();
    }

    private void init() {
        Config config = new Config(this);
        router = config.getRouter();
    }

    private void startLogInFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.navigate(mClass, this);
    }
}