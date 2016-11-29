package com.sasd13.proadmin.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.gui.form.TeacherForm;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.member.TeacherFromFormBuilder;
import com.sasd13.proadmin.ws.service.SettingsService;

import java.util.List;

public class SettingsActivity extends MotherActivity implements SettingsService.Caller {

    private View contentView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TeacherForm teacherForm;

    private Teacher teacher;

    private SettingsService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        contentView = findViewById(android.R.id.content);
        service = new SettingsService(this);

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
        service.readTeacher(SessionHelper.getExtraIdTeacherNumber(this));
    }

    private void buildFormSettings() {
        teacherForm = new TeacherForm(this);

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, teacherForm.getHolder());
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
        try {
            service.updateTeacher(getTeacherFromForm(), teacher);
        } catch (FormException e) {
            displayMessage(e.getMessage());
        }
    }

    private Teacher getTeacherFromForm() throws FormException {
        return new TeacherFromFormBuilder(teacherForm).build();
    }

    @Override
    public void onWaiting() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReaded(List<Teacher> teachersFromWS) {
        swipeRefreshLayout.setRefreshing(false);

        teacher = teachersFromWS.get(0);

        bindFormWithTeacher();
    }

    private void bindFormWithTeacher() {
        teacherForm.bindTeacher(teacher);
    }

    @Override
    public void onCreated() {
    }

    @Override
    public void onUpdated() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleted() {
    }

    @Override
    public void onErrors(List<String> errors) {
        swipeRefreshLayout.setRefreshing(false);
        displayMessage(WebServiceUtils.handleErrors(this, errors));
    }

    private void displayMessage(String error) {
        Snackbar.make(contentView, error, Snackbar.LENGTH_SHORT).show();
    }
}