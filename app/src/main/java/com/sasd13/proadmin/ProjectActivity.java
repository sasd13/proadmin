package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.gui.widget.recycler.tab.Tab;
import com.sasd13.proadmin.gui.widget.spin.Spin;
import com.sasd13.proadmin.ws.WebServiceFactory;

public class ProjectActivity extends MotherActivity {

    private Spin spin;
    private Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);

        createPresentationProject();
    }

    private void createPresentationProject() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Project project = (Project) WebServiceFactory.get("PROJECT").get(getProjectIdFromIntent());

            fillPresentationProject(project);
        } catch (NullPointerException e) {
            CustomDialog.showOkDialog(this, "Error", "Service not found");
        }
    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    private void fillPresentationProject(Project project) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_action_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}