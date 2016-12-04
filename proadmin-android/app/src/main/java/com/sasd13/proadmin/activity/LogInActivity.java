package com.sasd13.proadmin.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.LogInService;

import java.util.List;

public class LogInActivity extends AppCompatActivity implements LogInService.Caller {

    private static class LogInForm {
        EditText editTextNumber, editTextPassword;
    }

    private View contentView;
    private WaitDialog waitDialog;

    private LogInService logInService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);

        contentView = findViewById(android.R.id.content);
        logInService = new LogInService(this);

        buildView();
    }

    private void buildView() {
        buildFormLogIn();
    }

    private void buildFormLogIn() {
        LogInForm logInForm = new LogInForm();
        logInForm.editTextNumber = (EditText) findViewById(R.id.login_edittext_number);
        logInForm.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        buildButtonConnect(logInForm);
    }

    private void buildButtonConnect(final LogInForm logInForm) {
        Button button = (Button) findViewById(R.id.login_button_connect);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!logInForm.editTextNumber.getText().toString().trim().isEmpty()
                        && !logInForm.editTextPassword.getText().toString().trim().isEmpty()) {
                    String number = logInForm.editTextNumber.getText().toString().trim();
                    String password = HexEncoder.md5(logInForm.editTextPassword.getText().toString().trim());

                    logIn(number, password);
                }
            }
        });
    }

    private void logIn(String number, String password) {
        logInService.logIn(number, password);
    }

    @Override
    public void onWaiting() {
        waitDialog = new WaitDialog(this);
        waitDialog.show();
    }

    @Override
    public void onLoggedIn(Teacher teacherFromWS) {
        waitDialog.dismiss();
        SessionHelper.logIn(this, teacherFromWS);
    }

    @Override
    public void onErrors(List<String> errors) {
        waitDialog.dismiss();
        displayMessage(WebServiceUtils.handleErrors(this, errors));
    }

    private void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}