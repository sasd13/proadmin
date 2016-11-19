package com.sasd13.proadmin.activity;

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
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.gui.form.SettingsForm;
import com.sasd13.proadmin.service.member.TeacherManageService;
import com.sasd13.proadmin.service.member.TeacherReadService;
import com.sasd13.proadmin.util.SessionHelper;

public class SettingsActivity extends MotherActivity implements IReadServiceCaller<Teacher>, IManageServiceCaller<Teacher> {

    private View contentView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SettingsForm settingsForm;

    private Teacher teacher;

    private TeacherReadService teacherReadService;
    private TeacherManageService teacherManageService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        contentView = findViewById(android.R.id.content);
        teacherReadService = new TeacherReadService(this);
        teacherManageService = new TeacherManageService(this);

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
        teacherReadService.read(SessionHelper.getExtraIdTeacherNumber(this));
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
                updateTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeacher() {
        teacherManageService.update(settingsForm, teacher);
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReadSucceeded(Teacher teacher) {
        swipeRefreshLayout.setRefreshing(false);

        this.teacher = teacher;

        bindFormWithTeacher();
    }

    private void bindFormWithTeacher() {
        settingsForm.bindTeacher(teacher);
    }

    @Override
    public void onCreateSucceeded(Teacher teacher) {
    }

    @Override
    public void onUpdateSucceeded() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucceeded() {
    }

    @Override
    public void onError(@StringRes int message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}