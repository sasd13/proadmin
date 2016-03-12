package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RunningActivity extends MotherActivity {

    private static class RunningViewHolder {

    }

    private RunningViewHolder runningViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        createRunningViewHolder();
    }

    private void createRunningViewHolder() {
        runningViewHolder = new RunningViewHolder();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fillRunningViewHolder();
    }

    private void fillRunningViewHolder() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_project, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_project_action_runnings:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}