package com.sasd13.proadmin.activity.fragment.running;

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

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.List;

public class RunningNewFragment extends Fragment implements IManageServiceCaller<Running>, IReadServiceCaller<IReadWrapper<Project>> {

    private RunningsActivity parentActivity;

    private RunningForm runningForm;

    private Project project;

    private RunningManageService runningManageService;
    private ProjectReadService projectReadService;

    public static RunningNewFragment newInstance() {
        return new RunningNewFragment();
    }

    public static RunningNewFragment newInstance(Project project) {
        RunningNewFragment fragment = newInstance();
        fragment.project = project;

        return fragment;
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

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunning(view);
        bindFormWithRunning();
    }

    private void buildFormRunning(View view) {
        runningForm = new RunningForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    private void bindFormWithRunning() {
        runningForm.bindRunning(new DefaultRunningBuilder().build());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.menu_edit_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                createRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createRunning() {
        runningManageService.create(runningForm, SessionHelper.getExtraId(getContext(), Extra.ID_TEACHER_NUMBER));
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_running));
        readProjectsFromWS();
    }

    private void readProjectsFromWS() {
        projectReadService.readAll();
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Project> projectReadWrapper) {
        ProjectsSorter.byCode(projectReadWrapper.getWrapped());
        bindFormWithProjects(projectReadWrapper.getWrapped());
    }

    private void bindFormWithProjects(List<Project> projects) {
        if (project != null) {
            runningForm.bindProjects(projects, project);
        } else {
            runningForm.bindProjects(projects);
        }
    }

    @Override
    public void onCreateSucceeded(Running running) {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onUpdateSucceeded() {
    }

    @Override
    public void onDeleteSucceeded() {
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}