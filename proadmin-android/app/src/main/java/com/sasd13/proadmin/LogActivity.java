package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.KeyBoardHider;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.ws.task.RefreshableParameterizedReadTask;

import java.util.HashMap;
import java.util.Map;

public class LogActivity extends Activity implements IRefreshable {

    private class FormLogViewHolder {
        public EditText editTextNumber, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private View viewFormLog, viewLoad;
    private FormLogViewHolder formLog;

    private RefreshableParameterizedReadTask<Teacher> parameterizedReadTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log);
        createSwitchableViews();
        createFormLog();
    }

    private void createSwitchableViews() {
        viewFormLog = findViewById(R.id.log_view_formlog);
        viewLoad = findViewById(R.id.log_view_load);
    }

    private void createFormLog() {
        formLog = new FormLogViewHolder();

        formLog.editTextNumber = (EditText) findViewById(R.id.log_edittext_number);
        formLog.editTextPassword = (EditText) findViewById(R.id.log_edittext_password);
        formLog.buttonConnect = (Button) findViewById(R.id.log_button_connect);

        formLog.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLog.editTextNumber.getText().toString().trim().length() > 0
                        && formLog.editTextPassword.getText().toString().trim().length() > 0) {
                    KeyBoardHider.hide(LogActivity.this);
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String number = formLog.editTextNumber.getText().toString().trim();

        if (ConnectivityChecker.isActive(this)) {
            Map<String, String[]> parameters = new HashMap<>();
            parameters.put("number", new String[]{number});

            parameterizedReadTask = new RefreshableParameterizedReadTask<>(this, Teacher.class, parameters, this);
            parameterizedReadTask.execute();
        } else {
            ConnectivityChecker.showConnectivityError(this);
        }
    }

    @Override
    public void displayLoad() {
        switchToLoadView(true);
    }

    private void switchToLoadView(boolean switched) {
        if (switched) {
            viewLoad.setVisibility(View.VISIBLE);
            viewFormLog.setVisibility(View.GONE);
        } else {
            viewFormLog.setVisibility(View.VISIBLE);
            viewLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayContent() {
        String password = formLog.editTextPassword.getText().toString().trim();

        try {
            Teacher teacher = parameterizedReadTask.getContent()[0];

            if (teacher != null && teacher.getPassword().equals(password)) {
                Session.logIn(teacher.getId());
                Cache.keep(teacher);

                goToHomeActivity();
            } else {
                CustomDialog.showOkDialog(
                        this,
                        this.getResources().getString(R.string.log_dialog_title_error_log),
                        this.getResources().getString(R.string.log_dialog_message_error_log)
                );
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            displayNotFound();
        }
    }

    private void goToHomeActivity() {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                waitDialog.dismiss();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}