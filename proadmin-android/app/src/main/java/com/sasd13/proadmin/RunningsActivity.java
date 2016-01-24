package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.session.Session;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemRunning;
import com.sasd13.proadmin.ws.task.CreateTask;
import com.sasd13.proadmin.ws.task.RefreshableParameterizedReadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsActivity extends MotherActivity implements IRefreshable {

    private View viewTab, viewLoad;

    private Tab tab;
    private List<Running> runnings = new ArrayList<>();
    private RefreshableParameterizedReadTask<Running> parameterizedReadTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runnings);
        createSwicthableViews();
        createTabRunnings();
    }

    private void createSwicthableViews() {
        viewTab = findViewById(R.id.runnings_view_tab);
        viewLoad = findViewById(R.id.runnings_view_load);
    }

    private void createTabRunnings() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.runnings_recyclerview);

        tab = new Tab(this, recyclerView, R.layout.tabitem_running);
    }

    @Override
    protected void onStart() {
        super.onStart();

        refresh();
    }

    private void refresh() {
        if (ConnectivityChecker.isActive(this)) {
            runnings.clear();

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put("teacher", new String[]{String.valueOf(Session.getId())});
            parameters.put("project", new String[]{String.valueOf(getProjectIdFromIntent())});

            parameterizedReadTask = new RefreshableParameterizedReadTask<>(this, Running.class, parameters, this);
            parameterizedReadTask.execute();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
        }
    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_runnings, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_runnings_action_new:
                newRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void newRunning() {
        if ()
        Running running = new Running();

        running.setTeacher((Teacher) Cache.load(Session.getId(), Teacher.class));
        running.setProject((Project) Cache.load(getProjectIdFromIntent(), Project.class));
        running.setYear(Calendar.getInstance().get(Calendar.YEAR));

        CreateTask<Running> createTask = new CreateTask<>(this, Running.class);
    }

    @Override
    public void displayLoad() {
        switchToLoadView(true);
    }

    private void switchToLoadView(boolean switched) {
        if (switched) {
            viewLoad.setVisibility(View.VISIBLE);
            viewTab.setVisibility(View.GONE);
        } else {
            viewTab.setVisibility(View.VISIBLE);
            viewLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayContent() {
        try {
            for (Running running : parameterizedReadTask.getContent()) {
                runnings.add(running);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        fillTabRunnings();

        Cache.keepAll(runnings);
    }

    public void fillTabRunnings() {
        tab.clearItems();

        addRunningsToTab();

        switchToLoadView(false);
    }

    private void addRunningsToTab() {
        TabItemRunning tabItemRunning;
        Intent intent;

        for (Running running : runnings) {
            tabItemRunning = new TabItemRunning();

            tabItemRunning.setYear(String.valueOf(running.getYear()));

            intent = new Intent(this, RunningActivity.class);
            intent.putExtra(Extra.RUNNING_ID, running.getId());
            tabItemRunning.setIntent(intent);

            tab.addItem(tabItemRunning);
        }
    }

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}