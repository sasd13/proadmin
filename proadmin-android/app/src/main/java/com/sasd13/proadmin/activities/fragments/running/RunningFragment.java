package com.sasd13.proadmin.activities.fragments.running;

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

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;

import java.util.List;

public class RunningFragment extends Fragment implements IReadServiceCaller<List<Project>>, IManageServiceCaller<Running> {

    private RunningsActivity parentActivity;

    private RunningForm runningForm;
    private WaitDialog waitDialog;

    private boolean inModeEdit;
    private Running running;
    private Project project;

    private RunningManageService runningManageService;
    private ProjectReadService projectReadService;

    public static RunningFragment newInstance() {
        RunningFragment runningFragment = new RunningFragment();
        runningFragment.inModeEdit = false;

        return runningFragment;
    }

    public static RunningFragment newInstance(Project project) {
        RunningFragment runningFragment = new RunningFragment();
        runningFragment.inModeEdit = false;
        runningFragment.project = project;

        return runningFragment;
    }

    public static RunningFragment newInstance(Running running) {
        RunningFragment runningFragment = new RunningFragment();
        runningFragment.inModeEdit = true;
        runningFragment.running = running;

        return runningFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        projectReadService = new ProjectReadService(this);
        runningManageService = new RunningManageService(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_running, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);
        readProjectsFromWS();
    }

    private void buildFormRunning(View view) {
        runningForm = new RunningForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.running_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    private void readProjectsFromWS() {
        projectReadService.readProjects();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_running));
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
                if (inModeEdit) {
                    updateRunning();
                } else {
                    createRunning();
                }
                break;
            case R.id.menu_running_action_delete:
                deleteRunning();
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createRunning() {
        runningManageService.createRunning(runningForm, SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    private void updateRunning() {
        runningManageService.updateRunning(runningForm, running.getProject().getCode(), SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    private void deleteRunning() {
        runningManageService.deleteRunning(runningForm, SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    @Override
    public void onLoad() {
        if (!inModeEdit) {
            waitDialog = new WaitDialog(getContext());
            waitDialog.show();
        }
    }

    @Override
    public void onReadSucceeded(List<Project> projectsFromWS) {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

        ProjectsSorter.byCode(projectsFromWS);
        bindFormRunning(projectsFromWS);
    }

    private void bindFormRunning(List<Project> projects) {
        if (inModeEdit) {
            runningForm.bind(running, projects);
        } else {
            if (project != null) {
                runningForm.bind(new DefaultRunningBuilder(project).build(), projects);
            } else {
                runningForm.bind(new DefaultRunningBuilder().build(), projects);
            }
        }
    }

    @Override
    public void onCreateSucceeded(Running running) {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onUpdateSucceeded(Running running) {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucceeded() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onError(@StringRes int message) {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}