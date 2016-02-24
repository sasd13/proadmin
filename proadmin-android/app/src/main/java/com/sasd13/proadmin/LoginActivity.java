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
import com.sasd13.proadmin.ws.task.LoginTask;

public class LoginActivity extends Activity implements IRefreshable {

    private class FormLogViewHolder {
        public EditText editTextNumber, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private View viewLoad, viewFormLogin;
    private FormLogViewHolder formLogin;

    private LoginTask loginTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createSwitchableViews();
        createFormLogin();
    }

    private void createSwitchableViews() {
        viewLoad = findViewById(R.id.login_view_load);
        viewFormLogin = findViewById(R.id.login_view_formlogin);
    }

    private void createFormLogin() {
        formLogin = new FormLogViewHolder();

        formLogin.editTextNumber = (EditText) findViewById(R.id.login_edittext_number);
        formLogin.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);
        formLogin.buttonConnect = (Button) findViewById(R.id.login_button_connect);

        formLogin.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLogin.editTextNumber.getText().toString().trim().length() > 0
                        && formLogin.editTextPassword.getText().toString().trim().length() > 0) {
                    KeyBoardHider.hide(LoginActivity.this);
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String number = formLogin.editTextNumber.getText().toString().trim();
        String password = formLogin.editTextPassword.getText().toString().trim();

        if (ConnectivityChecker.isActive(this)) {
            loginTask = new LoginTask(this, number, password);
            loginTask.execute();
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
            viewFormLogin.setVisibility(View.GONE);
        } else {
            viewFormLogin.setVisibility(View.VISIBLE);
            viewLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayContent() {
        long id = loginTask.getContent();

        Session.logIn(id);
        goToHomeActivity();
    }

    private void goToHomeActivity() {
        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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