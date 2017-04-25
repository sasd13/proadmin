package com.sasd13.proadmin.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.provider.ControllerProvider;
import com.sasd13.proadmin.android.util.Extra;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.fragment.authentication.LogInFragment;

public class IdentityActivity extends AppCompatActivity {

    private ControllerProvider controllerProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controllerProvider = new ControllerProvider();

        setContentView(R.layout.layout_container);
        startLogInFragment();
    }

    private void startLogInFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return controllerProvider.provide(mClass, this);
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToMainActivity(final String userID, final String intermediary) {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                SessionHelper.setExtra(IdentityActivity.this, Extra.USERID, userID);
                SessionHelper.setExtra(IdentityActivity.this, Extra.INTERMEDIARY, intermediary);
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }
}