package com.sasd13.proadmin.activity.fragment.running;

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
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

import java.util.List;

public class RunningDetailsFragmentInfos extends Fragment implements IManageServiceCaller<Running>, IReadServiceCaller<IReadWrapper<Project>> {

    private RunningsActivity parentActivity;

    private RunningForm runningForm;

    private Running running;

    private RunningManageService runningManageService;
    private ProjectReadService projectReadService;

    public static RunningDetailsFragmentInfos newInstance(Running running) {
        RunningDetailsFragmentInfos fragment = new RunningDetailsFragmentInfos();
        fragment.running = running;

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
    }

    private void buildFormRunning(View view) {
        runningForm = new RunningForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    @Override
    public void onStart() {
        super.onStart();

        bindFormWithRunning();
        readProjectsFromWS();
    }

    private void bindFormWithRunning() {
        runningForm.bindRunning(running);
    }

    private void readProjectsFromWS() {
        projectReadService.readProjects();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateRunning();
                break;
            case R.id.menu_edit_action_delete:
                deleteRunning();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateRunning() {
        runningManageService.updateRunning(runningForm, running);
    }

    private void deleteRunning() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        runningManageService.deleteRunning(running);
                    }
                });
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
        runningForm.bindProjects(projects, running.getProject());
    }

    @Override
    public void onCreateSucceeded(Running running) {
    }

    @Override
    public void onUpdateSucceeded() {
        Snackbar.make(getView(), R.string.message_updated, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucceeded() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunnings();
    }

    @Override
    public void onError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}