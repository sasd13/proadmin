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

import proadmin.constant.Extra;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.manager.session.Session;

public class LogInActivity extends ActionBarActivity {

    private static final int LOADING_TIME_OUT = 2000;

    private class ViewHolder {
        public EditText editTextLogin, editTextPassword;
        public Button buttonLogin;
    }

    private ViewHolder formUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_login);

        //Set User form
        this.formUser = new ViewHolder();

        this.formUser.editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        this.formUser.buttonLogin = (Button) findViewById(R.id.login_button_connect);
        this.formUser.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formUser.editTextLogin.getText().toString().trim().length() > 0
                        && formUser.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
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
    protected void onStart() {
        super.onStart();

        if(getIntent().hasExtra(Extra.CLOSE) && getIntent().getBooleanExtra(Extra.CLOSE, false) == true) {
            getIntent().removeExtra(Extra.CLOSE);

            if(getIntent().hasExtra(Extra.TEACHER_FIRSTNAME)) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.WELCOME, true);
                intent.putExtra(Extra.TEACHER_FIRSTNAME, getIntent().getCharSequenceExtra(Extra.TEACHER_FIRSTNAME));

                startActivity(intent);
            }

            finish();
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

    public void logIn() {
        String login = this.formUser.editTextLogin.getEditableText().toString();
        String password = this.formUser.editTextPassword.getEditableText().toString();

        boolean connected = Session.logIn(login, password);

        if(!connected) {
            CustomDialog.showDialog(this, R.string.login_alertdialog_login_title_error, R.string.login_alertdialog_login_message_error, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };
            
            Handler handler = new Handler();
            handler.postDelayed(runnable, LOADING_TIME_OUT);
            dialog.show();
        }
    }
}