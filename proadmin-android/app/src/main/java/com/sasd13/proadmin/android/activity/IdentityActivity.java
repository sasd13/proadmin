package com.sasd13.proadmin.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.android.Configuration;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.Resolver;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.fragment.authentication.LogInFragment;

public class IdentityActivity extends AppCompatActivity {

    private Resolver resolver;

    public SessionStorage getSessionStorage() {
        return (SessionStorage) resolver.resolve(SessionStorage.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
    }

    private void init() {
        resolver = Configuration.init(this);

        startLogInFragment();
    }

    private void startLogInFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return resolver.resolveController(mClass, this);
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToMainActivity(final User user) {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, MainActivity.class);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                getSessionStorage().put(Constants.USERID, user.getUserID());
                getSessionStorage().put(Constants.INTERMEDIARY, user.getIntermediary());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(0);

        waitDialog.show();
    }
}