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
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.ws.task.LoginAsyncTask;

public class LogActivity extends Activity implements IRefreshable {

    private class FormLogViewHolder {
        public EditText editTextNumber, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private View viewFormLog, viewLoad;
    private FormLogViewHolder formLog;

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
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String number = formLog.editTextNumber.getText().toString().trim();
        String password = formLog.editTextPassword.getText().toString().trim();

        if (ConnectivityChecker.isActive(this)) {
            LoginAsyncTask loginTask = new LoginAsyncTask(this, number, password);
            loginTask.execute();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
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