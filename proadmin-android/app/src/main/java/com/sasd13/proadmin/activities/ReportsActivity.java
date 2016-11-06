package com.sasd13.proadmin.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;

public class ReportsActivity extends MotherActivity {

    private static class TeamViewHolder {

    }

    private TeamViewHolder teamViewHolder;

    private Team team;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_team);
        createTeamViewHolder();
    }

    private void createTeamViewHolder() {
        teamViewHolder = new TeamViewHolder();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (isInExtraModeEdit()) {
            String id = SessionHelper.getIntentExtraId(this, Extra.TEAM_NUMBER);
            //TODO : load team from savedInstance or parcel

            fillTeamViewHolder();
        }
    }

    private boolean isInExtraModeEdit() {
        return getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT;
    }

    private void fillTeamViewHolder() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_team, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_team_action_accept:
                if (isInExtraModeEdit()) {
                    updateTeam();
                } else {
                    createTeam();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void updateTeam() {

    }

    public void createTeam() {

    }
}