package com.sasd13.proadmin.activities.fragments.running;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.handler.RunningHandler;
import com.sasd13.proadmin.util.sorter.ProjectSorter;

import java.util.List;

public class RunningFragment extends Fragment {

    private Project project;
    private Running running;
    private RunningsActivity parentActivity;
    private RunningHandler runningHandler;
    private RunningForm runningForm;
    private boolean inModeEdit;
    private WaitDialog waitDialog;

    public static RunningFragment newInstance(Project project) {
        RunningFragment runningFragment = new RunningFragment();
        runningFragment.project = project;

        return runningFragment;
    }

    public void setRunning(Running running) {
        this.running = running;

        inModeEdit = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        runningHandler = new RunningHandler(this);

        createFormRunning();
    }

    private void createFormRunning() {
        List<Project> projects = runningHandler.readProjectsFromCache();

        ProjectSorter.byCode(projects);

        runningForm = new RunningForm(getContext(), projects);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_project, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);

        if (!inModeEdit) {
            setDefaultRunning();
        }

        refreshView();
    }

    private void buildFormRunning(View view) {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.project_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    private void refreshView() {
        runningForm.bind(running);
    }

    private void setDefaultRunning() {
        running = runningHandler.getDefaultValueOfRunning();
        running.setProject(project);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_running));
        parentActivity.getSupportActionBar().setSubtitle(getResources().getString(R.string.title_teams));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_running, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (!inModeEdit) {
            menu.findItem(R.id.menu_running_action_delete).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_running_action_save:
                saveOperation();
                break;
            case R.id.menu_running_action_delete:
                deleteOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void saveOperation() {
        if (inModeEdit) {
            runningHandler.updateRunning(running, runningForm);
        } else {
            runningHandler.createRunning(runningForm);
        }
    }

    public void deleteOperation() {
        OptionDialog.showYesNoDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        runningHandler.deleteRunning(running);
                    }
                }
        );
    }

    public void onLoad() {
        waitDialog = new WaitDialog(getContext());
        waitDialog.show();
    }

    public void onCreateSucceeded() {
        waitDialog.dismiss();
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    public void onUpdateSucceeded() {
        waitDialog.dismiss();
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    public void onDeleteSucceeded() {
        waitDialog.dismiss();
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    public void onError(@StringRes int message) {
        waitDialog.dismiss();
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}