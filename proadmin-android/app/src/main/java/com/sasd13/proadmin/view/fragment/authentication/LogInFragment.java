package com.sasd13.proadmin.view.fragment.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.controller.ILogInController;

public class LogInFragment extends Fragment {

    private static class LogInForm {
        EditText editTextNumber, editTextPassword;
    }

    private ILogInController controller;
    private LogInForm logInForm;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ILogInController) ((IdentityActivity) getActivity()).lookup(ILogInController.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_login, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildFormLogIn(view);
    }

    private void buildFormLogIn(View view) {
        logInForm = new LogInForm();
        logInForm.editTextNumber = (EditText) view.findViewById(R.id.login_edittext_number);
        logInForm.editTextPassword = (EditText) view.findViewById(R.id.login_edittext_password);

        buildButtonConnect(view);
    }

    private void buildButtonConnect(View view) {
        Button button = (Button) view.findViewById(R.id.login_button_connect);
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
        controller.logIn(number, password);
    }
}