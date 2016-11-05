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
import com.sasd13.proadmin.service.SettingsService;
import com.sasd13.proadmin.util.SessionHelper;

public class SettingsActivity extends MotherActivity {

    private SettingsService settingsService;
    private SettingsForm settingsForm;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View contentView;
    private Teacher teacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        settingsService = new SettingsService(this);
        contentView = findViewById(android.R.id.content);

        buildView();
    }

    private void buildView() {
        buildSwipeRefreshLayout();
        buildFormSettings();
        readTeacherFromWS();
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
        settingsService.readTeacher(SessionHelper.getExtraId(this, Extra.TEACHER_NUMBER));
    }

    private void buildFormSettings() {
        settingsForm = new SettingsForm(this);

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, settingsForm.getHolder());
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
                settingsService.updateTeacher(teacher, settingsForm);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void onReadSucceeded(Teacher teacher) {
        swipeRefreshLayout.setRefreshing(false);

        this.teacher = teacher;

        settingsForm.bindTeacher(teacher);
    }

    public void onUpdateSucceeded() {
        Snackbar.make(contentView, R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}