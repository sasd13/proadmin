package com.sasd13.proadmin.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.form.SettingsForm;
import com.sasd13.proadmin.handler.SettingsHandler;
import com.sasd13.proadmin.util.SessionHelper;

public class SettingsActivity extends MotherActivity {

    private SettingsHandler settingsHandler;
    private SettingsForm settingsForm;
    private Teacher teacher;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        settingsHandler = new SettingsHandler(this);
        settingsForm = new SettingsForm(this);
        contentView = findViewById(android.R.id.content);

        buildView();
    }

    private void buildView() {
        buildSwipeRefreshLayout();
        buildFormSettings();
        readTeacherFromCache();
        refreshView();
    }

    private void buildSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.settings_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readTeacherFromWS();
            }
        });
    }

    private void readTeacherFromWS() {
        settingsHandler.readTeacher(SessionHelper.getExtraId(SettingsActivity.this, Extra.TEACHER_ID));
    }

    private void buildFormSettings() {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, settingsForm.getHolder());
    }

    private void readTeacherFromCache() {
        teacher = settingsHandler.readTeacherFromCache(SessionHelper.getExtraId(this, Extra.TEACHER_ID));
    }

    private void refreshView() {
        settingsForm.bindTeacher(teacher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings_action_accept:
                updateTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeacher() {
        settingsHandler.updateTeacher(teacher, settingsForm);
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(Teacher teacherFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        teacher = teacherFromWS;

        refreshView();
    }

    public void onUpdateSucceeded() {
        Snackbar.make(contentView, R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}