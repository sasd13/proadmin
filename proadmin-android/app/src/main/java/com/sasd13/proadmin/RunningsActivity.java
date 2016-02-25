package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.session.Session;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.util.EnumURLParameter;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemRunning;
import com.sasd13.proadmin.ws.task.RefreshableCreateTask;
import com.sasd13.proadmin.ws.task.RefreshableParameterizedReadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsActivity extends MotherActivity implements IRefreshable {

    private View viewLoad, viewTab;
    private Tab tab;

    private RefreshableParameterizedReadTask<Running> parameterizedReadTask;
    private List<Running> runnings = new ArrayList<>();

    private RefreshableCreateTask<Running> createTask;
    private Running runningToCreate;
    private boolean isActionCreate;

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
            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(EnumURLParameter.TEACHER.getName(), new String[]{String.valueOf(Session.getId())});
            parameters.put(EnumURLParameter.PROJECT.getName(), new String[]{String.valueOf(getProjectIdFromIntent())});

            parameterizedReadTask = new RefreshableParameterizedReadTask<>(this, Running.class, parameters, this);
            parameterizedReadTask.execute();
        } else {
            ConnectivityChecker.showConnectivityError(this);
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
        if (ConnectivityChecker.isActive(this)) {
            isActionCreate = true;

            runningToCreate = new Running();
            runningToCreate.setTeacher((Teacher) Cache.load(Session.getId(), Teacher.class));
            runningToCreate.setProject((Project) Cache.load(getProjectIdFromIntent(), Project.class));
            runningToCreate.setYear(Calendar.getInstance().get(Calendar.YEAR));

            createTask = new RefreshableCreateTask<>(this, Running.class, this);
            createTask.execute(runningToCreate);
        } else {
            ConnectivityChecker.showConnectivityError(this);
        }
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
        if (isActionCreate) {
            isActionCreate = false;

            processResultForCreateTask();
        } else {
            processResultForReadTask();
        }

        switchToLoadView(false);
    }

    private void processResultForCreateTask() {
        try {
            long id = createTask.getContent()[0];

            if (id > 0) {
                runningToCreate.setId(id);
                runnings.add(runningToCreate);

                fillTabRunnings();
                Cache.keep(runningToCreate);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
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

    private void processResultForReadTask() {
        try {
            runnings.clear();

            Collections.addAll(runnings, parameterizedReadTask.getContent());
            fillTabRunnings();
            Cache.keepAll(runnings);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}