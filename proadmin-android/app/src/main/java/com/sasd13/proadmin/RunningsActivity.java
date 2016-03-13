package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.business.BusinessException;
import com.sasd13.proadmin.business.running.RunningService;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemRunning;
import com.sasd13.proadmin.handler.SessionHandler;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.util.sorter.running.RunningSorter;
import com.sasd13.proadmin.ws.task.LoaderCreateTask;
import com.sasd13.proadmin.ws.task.LoaderParameterizedReadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsActivity extends MotherActivity implements ILoader {

    private TextView textViewProject;
    private View viewLoad, viewTab;
    private Tab tabRunnings;

    private List<Running> runnings = new ArrayList<>();
    private LoaderParameterizedReadTask<Running> parameterizedReadTaskRunning;
    private Running runningToCreate;
    private LoaderCreateTask<Running> createTaskRunning;
    private boolean isActionCreateRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runnings);
        createTextViewProject();
        createSwicthableViews();
        createTabRunnings();
    }

    private void createTextViewProject() {
        textViewProject = (TextView) findViewById(R.id.runnings_textview_project);
    }

    private void createSwicthableViews() {
        viewTab = findViewById(R.id.runnings_view_tab);
        viewLoad = findViewById(R.id.runnings_view_load);
    }

    private void createTabRunnings() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.runnings_recyclerview);

        tabRunnings = new Tab(this, recyclerView, R.layout.tabitem_running);
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = SessionHandler.getExtraIdFromSession(Extra.PROJECT_ID);
        Project project = Cache.load(id, Project.class);

        fillTextViewProject(project);
        refresh();
    }

    private void fillTextViewProject(Project project) {
        String text = project.getCode() + " - " + project.getTitle();
        textViewProject.setText(text);
    }

    private void refresh() {
        if (ConnectivityChecker.isActive(this)) {
            long teacherId = SessionHandler.getExtraIdFromSession(Extra.TEACHER_ID);
            long projectId = SessionHandler.getExtraIdFromSession(Extra.PROJECT_ID);

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(Parameter.TEACHER.getName(), new String[]{ String.valueOf(teacherId) });
            parameters.put(Parameter.PROJECT.getName(), new String[]{ String.valueOf(projectId) });

            parameterizedReadTaskRunning = new LoaderParameterizedReadTask<>(this, Running.class, parameters, this);
            parameterizedReadTaskRunning.setDeepReadEnabled(true);
            parameterizedReadTaskRunning.execute();
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
            try {
                runningToCreate = RunningService.initCreateRunning();

                isActionCreateRunning = true;

                createTaskRunning = new LoaderCreateTask<>(this, Running.class, this);
                createTaskRunning.execute(runningToCreate);
            } catch (BusinessException e) {
                CustomDialog.showOkDialog(
                        this,
                        e.getTitle(),
                        e.getMessage()
                );
            }
        } else {
            ConnectivityChecker.showNotActive(this);
        }
    }

    @Override
    public void onLoad() {
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
    public void onCompleted() {
        if (isActionCreateRunning) {
            isActionCreateRunning = false;

            onCreateTaskRunningCompleted();
        } else {
            onReadTaskRunningsCompleted();
        }

        switchToLoadView(false);
    }

    private void onCreateTaskRunningCompleted() {
        try {
            long id = createTaskRunning.getResults().get(0);

            if (id > 0) {
                runningToCreate.setId(id);
                runnings.add(runningToCreate);

                fillTabRunnings();
                Toast.makeText(this, "Gestion créée", Toast.LENGTH_SHORT).show();
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
        tabRunnings.clearItems();

        RunningSorter.byYear(runnings, true);
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

            tabRunnings.addItem(tabItemRunning);
        }
    }

    private void onReadTaskRunningsCompleted() {
        runnings.clear();
        runnings.addAll(parameterizedReadTaskRunning.getResults());

        fillTabRunnings();
        Cache.keepAll(runnings);
    }

    @Override
    public void onError() {
        switchToLoadView(false);
    }
}