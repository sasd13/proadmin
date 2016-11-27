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
import com.sasd13.proadmin.gui.form.SettingsForm;
import com.sasd13.proadmin.service.ManageService;
import com.sasd13.proadmin.service.ReadService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.caller.IManageWebServiceCaller;
import com.sasd13.proadmin.ws.caller.IReadWebServiceCaller;

import java.util.List;

public class SettingsActivity extends MotherActivity implements IReadWebServiceCaller<Teacher>, IManageWebServiceCaller {

    private View contentView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SettingsForm settingsForm;

    private Teacher teacher;

    private ReadService<Teacher> readService;
    private ManageService<Teacher> manageService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        contentView = findViewById(android.R.id.content);
        readService = new ReadService<>(this, WSResources.URL_WS_TEACHERS, this, Teacher.class);
        manageService = new ManageService<>(this, WSResources.URL_WS_TEACHERS, this);

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
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(this)});
        readService.read();
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
        try {
            manageService.update(getUpdateWrapper());
        } catch (FormException e) {
            onError(e.getMessage());
        }
    }

    private TeacherUpdateWrapper getUpdateWrapper() throws FormException {
        TeacherUpdateWrapper wrapper = new TeacherUpdateWrapper();

        wrapper.setWrapped(getTeacherFromForm());
        wrapper.setNumber(teacher.getNumber());

        return wrapper;
    }

    private Teacher getTeacherFromForm() throws FormException {
        Teacher teacher = new Teacher();

        teacher.setNumber(settingsForm.getNumber());
        teacher.setFirstName(settingsForm.getFirstName());
        teacher.setLastName(settingsForm.getLastName());
        teacher.setEmail(settingsForm.getEmail());

        return teacher;
    }

    @Override
    public void onWaiting() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onReaded(List<Teacher> teachers) {
        swipeRefreshLayout.setRefreshing(false);

        teacher = teachers.get(0);

        bindFormWithTeacher();
    }

    private void bindFormWithTeacher() {
        settingsForm.bindTeacher(teacher);
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
    public void onError(String message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}