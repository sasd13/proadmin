package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemTeam;
import com.sasd13.proadmin.helper.ActivityHelper;
import com.sasd13.proadmin.helper.SessionHelper;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.ws.task.LoaderParameterizedReadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningActivity extends MotherActivity implements ILoader {

    private static class RunningViewHolder {
        TextView textViewProject, textViewYear;
    }

    private RunningViewHolder runningViewHolder;
    private View viewLoad, viewTab;
    private Tab tabTeams;
    private Button buttonNewGroup;

    private Running running;
    private List<Team> teams = new ArrayList<>();
    private LoaderParameterizedReadTask<RunningTeam> readTaskRunningTeams;
    private LoaderParameterizedReadTask<Team> readTaskTeams;
    private boolean isActionReadRunningTeams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_running);
        createRunningViewHoler();
        createSwicthableViews();
        createTabTeams();
    }

    private void createRunningViewHoler() {
        runningViewHolder = new RunningViewHolder();
        runningViewHolder.textViewProject = (TextView) findViewById(R.id.running_textview_project);
        runningViewHolder.textViewYear = (TextView) findViewById(R.id.running_textview_year);
    }

    private void createSwicthableViews() {
        viewLoad = findViewById(R.id.running_view_load);
        viewTab = findViewById(R.id.running_view_tab);
    }

    private void createTabTeams() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.running_recyclerview);

        tabTeams = new Tab(this, recyclerView, R.layout.tabitem_team);
    }

    private void newTeam() {
        Intent intent = new Intent(this, Team.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = ActivityHelper.getCurrentExtraId(this, Extra.RUNNING_ID);
        running = Cache.load(id, Running.class);

        long projectId = SessionHelper.getExtraIdFromSession(Extra.PROJECT_ID);
        Project project = Cache.load(projectId, Project.class);

        fillRunningViewHolder(project);
        refresh();
    }

    private void fillRunningViewHolder(Project project) {
        String text = project.getCode() + " - " + project.getTitle();

        runningViewHolder.textViewProject.setText(text);
        runningViewHolder.textViewYear.setText(String.valueOf(running.getYear()));
    }

    private void refresh() {
        isActionReadRunningTeams = true;

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.RUNNING.getName(), new String[]{ String.valueOf(running.getId()) });

        readTaskRunningTeams = new LoaderParameterizedReadTask<>(this, RunningTeam.class, parameters, this);
        readTaskRunningTeams.setDeepReadEnabled(true);
        readTaskRunningTeams.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_running, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_running_action_refresh:
                refresh();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
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
        if (isActionReadRunningTeams) {
            isActionReadRunningTeams = false;

            onReadTaskRunningTeamsCompleted();
        } else {
            onReadTaskTeamsCompleted();
        }
    }

    private void onReadTaskRunningTeamsCompleted() {
        List<RunningTeam> runningTeams = readTaskRunningTeams.getResults();
        if (!runningTeams.isEmpty()) {
            Map<String, String[]> parameters = new HashMap<>();
            for (RunningTeam runningTeam : runningTeams) {
                parameters.put(Parameter.ID.getName(), new String[]{ String.valueOf(runningTeam.getTeam().getId()) });
            }

            readTaskTeams = new LoaderParameterizedReadTask<>(this, Team.class, parameters, this);
            readTaskTeams.execute();
        } else {
            switchToLoadView(false);
        }
    }

    private void onReadTaskTeamsCompleted() {
        teams.clear();
        teams.addAll(readTaskTeams.getResults());

        fillTabTeams();
        Cache.keepAll(teams);
        Cache.keepAll(readTaskRunningTeams.getResults());
        switchToLoadView(false);
    }

    private void fillTabTeams() {
        tabTeams.clearItems();

        addTeamsToTab();
    }

    private void addTeamsToTab() {
        TabItemTeam tabItemTeam;
        Intent intent;

        for (Team team : teams) {
            tabItemTeam = new TabItemTeam();
            tabItemTeam.setCode(team.getCode());

            intent = new Intent(this, TeamActivity.class);
            intent.putExtra(Extra.TEAM_ID, team.getId());
            tabItemTeam.setIntent(intent);

            tabTeams.addItem(tabItemTeam);
        }
    }

    @Override
    public void onError() {
        switchToLoadView(false);
    }
}