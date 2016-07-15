package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.FormType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.content.form.SettingsForm;
import com.sasd13.proadmin.content.handler.SettingsHandler;

public class SettingsActivity extends MotherActivity {

    private SettingsHandler settingsHandler;
    private SettingsForm settingsForm;
    private Teacher teacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        settingsHandler = new SettingsHandler(this);
        settingsForm = new SettingsForm(this);

        buildSettingsView();
    }

    private void buildSettingsView() {
        Recycler form = RecyclerFactory.makeBuilder(FormType.FORM).build();
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, settingsForm.getHolder());
    }

    @Override
    protected void onStart() {
        super.onStart();

        readTeacher();
    }

    private void readTeacher() {
        teacher = settingsHandler.readTeacher();

        settingsForm.bindTeacher(teacher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting_action_accept:
                updateTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeacher() {
        settingsHandler.updateTeacher(teacher);
    }
}