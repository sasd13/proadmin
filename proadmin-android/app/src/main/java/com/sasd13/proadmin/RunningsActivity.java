package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.util.NetworkHelper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.content.business.RunningBusiness;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.RunningSorter;
import com.sasd13.proadmin.ws.task.CreateTask;
import com.sasd13.proadmin.ws.task.ParameterizedReadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningsActivity extends MotherActivity implements Promise {

    private TextView textViewProject;
    private View viewLoad, viewTab;
    private Recycler tab;

    private List<Running> runnings = new ArrayList<>();
    private ParameterizedReadTask<Running> parameterizedReadTaskRunning;
    private Running runningToCreate;
    private CreateTask<Running> createTaskRunning;
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
        //tabRunnings = new Tab((RecyclerView) findViewById(R.id.runnings_recyclerview));
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = SessionHelper.getExtraIdFromSession(this, Extra.PROJECT_ID);
        Project project = Cache.load(id, Project.class);

        fillTextViewProject(project);
        refresh();
    }

    private void fillTextViewProject(Project project) {
        String text = project.getCode() + " - " + project.getTitle();

        textViewProject.setText(text);
    }

    private void refresh() {
        if (NetworkHelper.isConnected(this)) {
            long teacherId = SessionHelper.getExtraIdFromSession(this, Extra.TEACHER_ID);
            long projectId = SessionHelper.getExtraIdFromSession(this, Extra.PROJECT_ID);

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(EnumParameter.TEACHER.getName(), new String[]{ String.valueOf(teacherId) });
            parameters.put(EnumParameter.PROJECT.getName(), new String[]{ String.valueOf(projectId) });

            parameterizedReadTaskRunning = new ParameterizedReadTask<>(this, Running.class, parameters);
            parameterizedReadTaskRunning.setDeepReadEnabled(true);
            parameterizedReadTaskRunning.execute();
        } else {
            NetworkHelper.displayMessageNotConnected(this);
        }
    }

    private void newRunning() {
        if (NetworkHelper.isConnected(this)) {
            runningToCreate = new Running();

            boolean isPrepared = RunningBusiness.prepareRunningToCreate(runningToCreate, this);
            if (isPrepared) {
                isActionCreateRunning = true;

                createTaskRunning = new CreateTask<>(this, Running.class);
                createTaskRunning.execute(runningToCreate);
            }
        } else {
            NetworkHelper.displayMessageNotConnected(this);
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
    public void onSuccess() {
        if (isActionCreateRunning) {
            isActionCreateRunning = false;

            createTaskRunningSucceeded();
        } else {
            readTaskRunningsSucceeded();
        }

        switchToLoadView(false);
    }

    private void createTaskRunningSucceeded() {
        try {
            long id = createTaskRunning.getResults().get(0);

            if (id > 0) {
                runningToCreate.setId(id);
                runnings.add(runningToCreate);

                fillTabRunnings();
                Toast.makeText(this, "Running added", Toast.LENGTH_SHORT).show();
                Cache.keep(runningToCreate);
            }
        } catch (IndexOutOfBoundsException e) {
            OptionDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Erreur de chargement des données"
            );
        }
    }

    public void fillTabRunnings() {
        tab.clear();

        RunningSorter.byYear(runnings, true);
        //addRunningsToTab();
    }

    /*
    private void addRunningsToTab() {
        RunningItem tabItemRunning;

        for (final Running running : runnings) {
            tabItemRunning = new RunningItem();
            tabItemRunning.setLabel("Running");
            tabItemRunning.setYear(String.valueOf(running.getYear()));
            tabItemRunning.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    Intent intent = new Intent(RunningsActivity.this, RunningActivity.class);
                    intent.putExtra(Extra.RUNNING_ID, running.getId());

                    startActivity(intent);
                }
            });

            tabRunnings.addItem(tabItemRunning);
        }
    }*/

    private void readTaskRunningsSucceeded() {
        runnings.clear();
        runnings.addAll(parameterizedReadTaskRunning.getResults());

        fillTabRunnings();
        Cache.keepAll(runnings);
    }

    @Override
    public void onFail() {
        switchToLoadView(false);
    }
}