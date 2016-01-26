package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.KeyBoardHider;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.ws.task.LogTask;

public class LogActivity extends Activity implements IRefreshable {

    private class FormLogViewHolder {
        public EditText editTextNumber, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private View viewFormLog, viewLoad;
    private FormLogViewHolder formLog;

    private LogTask logTask;

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
        String password = formLog.editTextPassword.getText().toString().trim();

        if (ConnectivityChecker.isActive(this)) {
            logTask = new LogTask(this, number, password);
            logTask.execute();
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
        long id = logTask.getContent();

        Session.logIn(id);

        goToHomeActivity();
    }

    private void goToHomeActivity() {
        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        }, TIMEOUT);

        taskPlanner.start();
    }

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}