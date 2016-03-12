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
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.util.Parameter;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemRunning;
import com.sasd13.proadmin.handler.SessionHandler;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.ws.task.LoaderCreateTask;
import com.sasd13.proadmin.ws.task.LoaderParameterizedReadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsActivity extends MotherActivity implements ILoader {

    private View viewLoad, viewTab;
    private Tab tab;

    private LoaderParameterizedReadTask<Running> parameterizedReadTask;
    private List<Running> runnings = new ArrayList<>();

    private LoaderCreateTask<Running> createTask;
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
            long teacherId = SessionHandler.getExtraIdFromSession(Extra.TEACHER_ID);
            long projectId = SessionHandler.getExtraIdFromSession(Extra.PROJECT_ID);

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(Parameter.TEACHER.getName(), new String[]{String.valueOf(teacherId)});
            parameters.put(Parameter.PROJECT.getName(), new String[]{String.valueOf(projectId)});

            parameterizedReadTask = new LoaderParameterizedReadTask<>(this, Running.class, parameters, this);
            parameterizedReadTask.setDeepReadEnabled(true);
            parameterizedReadTask.execute();
        } else {
            ConnectivityChecker.showNotActive(this);
        }
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

            long teacherId = SessionHandler.getExtraIdFromSession(Extra.TEACHER_ID);
            long projectId = SessionHandler.getExtraIdFromSession(Extra.PROJECT_ID);

            runningToCreate = new Running();
            runningToCreate.setTeacher(Cache.load(teacherId, Teacher.class));
            runningToCreate.setProject(Cache.load(projectId, Project.class));

            createTask = new LoaderCreateTask<>(this, Running.class, this);
            createTask.execute(runningToCreate);
        } else {
            ConnectivityChecker.showNotActive(this);
        }
    }

    @Override
    public void doInLoad() {
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
    public void doInCompleted() {
        if (isActionCreate) {
            isActionCreate = false;

            doInCreateTaskCompleted();
        } else {
            doInReadTaskCompleted();
        }

        switchToLoadView(false);
    }

    private void doInCreateTaskCompleted() {
        try {
            long id = createTask.getResults().get(0);

            if (id > 0) {
                runningToCreate.setId(id);
                runnings.add(runningToCreate);

                fillTabRunnings();
                Cache.keep(runningToCreate);
            }
        } catch (IndexOutOfBoundsException e) {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Erreur de chargement des données"
            );
        }
    }

    public void fillTabRunnings() {
        tab.clearItems();

        addRunningsToTab();
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

    private void doInReadTaskCompleted() {
        runnings.clear();
        runnings.addAll(parameterizedReadTask.getResults());

        fillTabRunnings();
        Cache.keepAll(runnings);
    }

    @Override
    public void doInError() {
        switchToLoadView(false);
    }
}