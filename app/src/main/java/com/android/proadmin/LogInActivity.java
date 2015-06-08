package com.android.proadmin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proadmin.R;

import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.tool.FormValidator;
import proadmin.tool.FormValidatorCode;
import proadmin.tool.Session;

public class LogInActivity extends ActionBarActivity {

    private static final int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private class ViewHolder {
        public EditText loginEditText, passwordEditText;
        public Button connectButton;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_login);

        //Set User form
        this.form = new ViewHolder();

        this.form.loginEditText = (EditText) findViewById(R.id.login_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.login_edittext_password);

        this.form.connectButton = (Button) findViewById(R.id.login_button_connect);
        this.form.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (form.loginEditText.getText().toString().trim().length() > 0
                        && form.passwordEditText.getText().toString().trim().length() > 0) {
                    startConnection();
                }
            }
        });

        //Add underline and link for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.login_textview_restorepassword),
                (TextView) findViewById(R.id.login_textview_signup)
        };

        SpannableString text;
        for(TextView textView : textViews) {
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.login_textview_restorepassword :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LogInActivity.this, RestorePasswordActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.login_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void startConnection() {
        String login = this.form.loginEditText.getEditableText().toString();
        String password = this.form.passwordEditText.getEditableText().toString();

        boolean connected = false;
        FormValidatorCode codeEmail = FormValidator.validEmail(login);
        if(codeEmail == FormValidatorCode.OK) {
            connected = Session.logIn(login, password);
        }

        if(!connected) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.login_alertdialog_login_title_error)
                    .setMessage(R.string.login_alertdialog_login_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };
            
            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, LOADING_TIME_OUT);
            dialog.show();
        }
    }
}