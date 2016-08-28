package com.sasd13.proadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.handler.LogInHandler;
import com.sasd13.proadmin.util.SessionHelper;

public class LogInActivity extends AppCompatActivity {

    private class LogInForm {
        public EditText editTextNumber, editTextPassword;
    }

    private LogInHandler logInHandler;
    private WaitDialog waitDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        logInHandler = new LogInHandler(this);

        buildView();
    }

    private void buildView() {
        LogInForm logInForm = new LogInForm();
        logInForm.editTextNumber = (EditText) findViewById(R.id.login_edittext_number);
        logInForm.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        createButtonConnect(logInForm);
    }

    private void createButtonConnect(final LogInForm logInForm) {
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
        logInHandler.logIn(number, password);
    }

    public void onLoad() {
        waitDialog = new WaitDialog(this);
        waitDialog.show();
    }

    public void onLogInSucceeded(Teacher teacher) {
        waitDialog.dismiss();
        SessionHelper.logIn(this, teacher);
    }

    public void onError(String message) {
        waitDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}