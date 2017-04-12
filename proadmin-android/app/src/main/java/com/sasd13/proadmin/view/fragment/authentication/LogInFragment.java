package com.sasd13.proadmin.view.fragment.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.view.ILogInController;

public class LogInFragment extends Fragment {

    private static class LogInForm {
        EditText editTextUsername, editTextPassword;
    }

    private ILogInController controller;
    private LogInForm logInForm;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
        logInForm.editTextUsername = (EditText) view.findViewById(R.id.login_edittext_username);
        logInForm.editTextPassword = (EditText) view.findViewById(R.id.login_edittext_password);

        buildButtonConnect(view);
    }

    private void buildButtonConnect(View view) {
        Button button = (Button) view.findViewById(R.id.login_button_connect);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        String username = logInForm.editTextUsername.getText().toString().trim();
        String password = logInForm.editTextPassword.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            controller.logIn(username, password);
        }
    }
}