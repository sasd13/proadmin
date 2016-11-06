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
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.pattern.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.SessionHelper;

public class RunningNewFragment extends Fragment implements IManageServiceCaller<Running> {

    private Project project;
    private RunningsActivity parentActivity;
    private RunningManageService runningManageService;
    private RunningForm runningForm;
    private WaitDialog waitDialog;

    public static RunningNewFragment newInstance(Project project) {
        RunningNewFragment runningFragment = new RunningNewFragment();
        runningFragment.project = project;

        return runningFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningsActivity) getActivity();
        runningManageService = new RunningManageService(this);
        runningForm = new RunningForm(getContext());
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
        setDefaultRunning();
    }

    private void buildFormRunning(View view) {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.descriptor_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    private void setDefaultRunning() {
        runningForm.bind(new DefaultRunningBuilder(project).build());
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

        menu.findItem(R.id.menu_running_action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_running_action_save:
                createRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void createRunning() {
        runningManageService.createRunning(runningForm, project.getCode(), SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    public void onLoad() {
        waitDialog = new WaitDialog(getContext());
        waitDialog.show();
    }

    @Override
    public void onCreateSucceeded(Running running) {
        waitDialog.dismiss();
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onUpdateSucceeded(Running running) {

    }

    @Override
    public void onDeleteSucceeded() {

    }

    @Override
    public void onError(@StringRes int message) {
        waitDialog.dismiss();
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}