package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.ActivityHelper;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.task.ParameterizedReadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningActivity extends MotherActivity implements Promise {

    private static class RunningViewHolder {
        TextView textViewProject, textViewYear;
    }

    private RunningViewHolder runningViewHolder;
    private View viewLoad, viewTab;
    private Recycler tab;

    private Running running;
    private List<Team> teams = new ArrayList<>();
    private ParameterizedReadTask<RunningTeam> readTaskRunningTeams;
    private ParameterizedReadTask<Team> readTaskTeams;
    private boolean isActionReadRunningTeams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_running);
        createRunningViewHoler();
        createSwitchableViews();
        createTabTeams();
    }

    private void createRunningViewHoler() {
        runningViewHolder = new RunningViewHolder();
        runningViewHolder.textViewProject = (TextView) findViewById(R.id.running_textview_project);
        runningViewHolder.textViewYear = (TextView) findViewById(R.id.running_textview_year);
    }

    private void createSwitchableViews() {
        viewLoad = findViewById(R.id.running_view_load);
        viewTab = findViewById(R.id.running_view_tab);
    }

    private void createTabTeams() {
        //tabTeams = new Tab((RecyclerView) findViewById(R.id.running_recyclerview));
    }

    private void newTeam() {
        startActivity(new Intent(this, Team.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = ActivityHelper.getCurrentExtraId(this, Extra.RUNNING_ID);
        running = Cache.load(id, Running.class);

        long projectId = SessionHelper.getExtraIdFromSession(this, Extra.PROJECT_ID);
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
        parameters.put(EnumParameter.RUNNING.getName(), new String[]{ String.valueOf(running.getId()) });

        readTaskRunningTeams = new ParameterizedReadTask<>(this, RunningTeam.class, parameters);
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
    public void onSuccess() {
        if (isActionReadRunningTeams) {
            isActionReadRunningTeams = false;

            readTaskRunningTeamsSucceeded();
        } else {
            readTaskTeamsSucceeded();
        }
    }

    private void readTaskRunningTeamsSucceeded() {
        List<RunningTeam> runningTeams = readTaskRunningTeams.getResults();
        if (!runningTeams.isEmpty()) {
            Map<String, String[]> parameters = new HashMap<>();
            for (RunningTeam runningTeam : runningTeams) {
                parameters.put(EnumParameter.ID.getName(), new String[]{ String.valueOf(runningTeam.getTeam().getId()) });
            }

            readTaskTeams = new ParameterizedReadTask<>(this, Team.class, parameters);
            readTaskTeams.execute();
        } else {
            switchToLoadView(false);
        }
    }

    private void readTaskTeamsSucceeded() {
        teams.clear();
        teams.addAll(readTaskTeams.getResults());

        fillTabTeams();
        Cache.keepAll(teams);
        Cache.keepAll(readTaskRunningTeams.getResults());
        switchToLoadView(false);
    }

    private void fillTabTeams() {
        tab.clear();

        //addTeamsToTab();
    }

    /*
    private void addTeamsToTab() {
        TeamItem tabItemTeam;
        Intent intent;

        for (final Team team : teams) {
            tabItemTeam = new TeamItem();
            tabItemTeam.setLabel(team.getCode());
            tabItemTeam.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    Intent intent = new Intent(RunningActivity.this, TeamActivity.class);
                    intent.putExtra(Extra.TEAM_ID, team.getId());

                    startActivity(intent);
                }
            });

            tabTeams.addItem(tabItemTeam);
        }
    }*/

    @Override
    public void onFail() {
        switchToLoadView(false);
    }
}