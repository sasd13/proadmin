package com.android.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proadmin.R;

import proadmin.constant.Extra;
import proadmin.db.accessor.DataAccessor;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.session.Session;

public class SettingsActivity extends ActionBarActivity {

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    }

    private ViewHolder formUser;

    private DataAccessor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.form_user_edittext_password);
        this.formUser.editTextConfirmPassword = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);

        Button buttonLogout = (Button) findViewById(R.id.settings_button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Button buttonReset = (Button) findViewById(R.id.settings_button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void logOut() {
        if (Session.logOut()) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Extra.EXIT, true);

            startActivity(intent);
            finish();
        } else {
            CustomDialog.showDialog(
                    this,
                    "Error logout",
                    "You have not been logged out",
                    CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                    null
            );
        }
    }

    public void reset() {

    }
}
