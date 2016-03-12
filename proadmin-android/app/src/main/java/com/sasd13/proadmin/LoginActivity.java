package com.sasd13.proadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.util.KeyBoardHider;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.handler.SessionHandler;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.ws.task.LoaderReadTask;
import com.sasd13.proadmin.ws.task.LoginTask;

public class LoginActivity extends Activity implements ILoader {

    private static class FormLoginViewHolder {
        EditText editTextNumber, editTextPassword;
        Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private View viewLoad, viewFormLogin;
    private FormLoginViewHolder formLoginViewHolder;

    private LoginTask loginTask;
    private LoaderReadTask<Teacher> readTask;
    private boolean isActionLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createSwitchableViews();
        createFormLoginViewHolder();
    }

    private void createSwitchableViews() {
        viewLoad = findViewById(R.id.login_view_load);
        viewFormLogin = findViewById(R.id.login_view_formlogin);
    }

    private void createFormLoginViewHolder() {
        formLoginViewHolder = new FormLoginViewHolder();
        formLoginViewHolder.editTextNumber = (EditText) findViewById(R.id.login_edittext_number);
        formLoginViewHolder.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);
        formLoginViewHolder.buttonConnect = (Button) findViewById(R.id.login_button_connect);

        formLoginViewHolder.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLoginViewHolder.editTextNumber.getText().toString().trim().length() > 0
                        && formLoginViewHolder.editTextPassword.getText().toString().trim().length() > 0) {
                    KeyBoardHider.hide(LoginActivity.this);
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        if (ConnectivityChecker.isActive(this)) {
            isActionLogin = true;

            String number = formLoginViewHolder.editTextNumber.getText().toString().trim();
            String password = formLoginViewHolder.editTextPassword.getText().toString().trim();

            loginTask = new LoginTask(this, number, password);
            loginTask.execute();
        } else {
            ConnectivityChecker.showNotActive(this);
        }
    }

    @Override
    public void doInLoad() {
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
    public void doInCompleted() {
        if (isActionLogin) {
            isActionLogin = false;

            doInLoginTaskCompleted();
        } else {
            doInReadTaskCompleted();
        }
    }

    private void doInLoginTaskCompleted() {
        long result = loginTask.getResult();

        if (result == 0) {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Identifiant invalide"
            );

            switchToLoadView(false);
        } else if (result == -1) {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Mot de passe incorrect"
            );

            switchToLoadView(false);
        } else {
            readTask = new LoaderReadTask<>(this, Teacher.class, this);
            readTask.execute(result);
        }
    }

    private void doInReadTaskCompleted() {
        try {
            Cache.keep(readTask.getResults().get(0));
            SessionHandler.setExtraIdInSession(Extra.TEACHER_ID, loginTask.getResult());
            goToHomeActivity();
        } catch (IndexOutOfBoundsException e) {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Erreur de chargement des données"
            );

            switchToLoadView(false);
        }
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
    public void doInError() {
        switchToLoadView(false);
    }
}