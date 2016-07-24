package com.sasd13.proadmin;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.RecyclerHelper;
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
    private boolean runSwipeRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        settingsHandler = new SettingsHandler(this);
        settingsForm = new SettingsForm(this);

        buildView();
    }

    private void buildView() {
        buildSwipeRefreshLayout();
        buildSettingsForm();

        readTeacher(false);
    }

    private void buildSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.settings_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readTeacher(true);
            }
        });
    }

    private void buildSettingsForm() {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, settingsForm.getHolder());
    }

    private void readTeacher(boolean runSwipeRefresh) {
        this.runSwipeRefresh = runSwipeRefresh;

        settingsHandler.readTeacher(SessionHelper.getExtraId(this, Extra.TEACHER_ID));
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
            case R.id.menu_settings_action_refresh:
                readTeacher(true);
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
        if (runSwipeRefresh) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void onReadSucceeded(Teacher teacher) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        fillView(teacher);
    }

    private void fillView(Teacher teacher) {
        this.teacher = teacher;

        settingsForm.bindTeacher(teacher);
    }

    public void onUpdateSucceeded() {
        Toast.makeText(this, getResources().getString(R.string.message_saved), Toast.LENGTH_SHORT).show();
    }

    public void onError(String message) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        fillView(settingsHandler.readTeacherFromCache(SessionHelper.getExtraId(this, Extra.TEACHER_ID)));

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}