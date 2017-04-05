package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.IController;
import com.sasd13.proadmin.view.fragment.ProxyFragment;
import com.sasd13.proadmin.view.fragment.authentication.LogInFragment;
import com.sasd13.proadmin.provider.ControllerProvider;
import com.sasd13.proadmin.util.Extra;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.Stack;

public class IdentityActivity extends AppCompatActivity {

    private Stack<Fragment> stack = new Stack<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);
        showAuthentication();
    }

    private void showAuthentication() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public IController lookup(Class<? extends IController> mClass) {
        return ControllerProvider.provide(mClass, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (!stack.isEmpty()) {
            stack.pop();

            if (!stack.isEmpty() && ProxyFragment.class.isAssignableFrom(stack.peek().getClass())) {
                onBackPressed();
            }
        }
    }

    public void startFragment(Fragment fragment) {
        stack.push(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToMainActivity(final Teacher teacher) {
        SessionHelper.setExtraId(this, Extra.ID_TEACHER_NUMBER, teacher.getNumber());

        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

    public void clearHistory() {
        if (!stack.isEmpty()) {
            stack.clear();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}