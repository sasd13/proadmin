package com.sasd13.proadmin.activities.fragments.team;

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
import com.sasd13.proadmin.activities.RunningsActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.service.running.RunningManageService;
import com.sasd13.proadmin.util.RunningTeamWrapper;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;

import java.util.List;

public class TeamDetailsFragmentInfos extends Fragment implements IManageServiceCaller<RunningTeam>, IReadServiceCaller<RunningTeamWrapper> {

    private RunningsActivity parentActivity;

    private RunningTeamForm runningTeamForm;

    private RunningTeam runningTeam;

    private RunningManageService runningManageService;
    private ProjectReadService projectReadService;

    public static TeamDetailsFragmentInfos newInstance(RunningTeam runningTeam) {
        TeamDetailsFragmentInfos fragment = new TeamDetailsFragmentInfos();
        fragment.runningTeam = runningTeam;

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
        runningTeamForm = new RunningTeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningForm.getHolder());
    }

    @Override
    public void onStart() {
        super.onStart();

        readProjectsFromWS();
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
        runningManageService.updateRunning(runningTeamForm, running.getProject().getCode(), SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
    }

    private void deleteRunning() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        runningManageService.deleteRunning(runningTeamForm, SessionHelper.getExtraId(getContext(), Extra.TEACHER_NUMBER));
                    }
                });
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadSucceeded(RunningTeamWrapper runningTeamWrapper) {
        List<Project> projects = runningTeamWrapper.getProjects();
        List<AcademicLevel> academicLevels = runningTeamWrapper.getAcademicLevels();

        ProjectsSorter.byCode(projects);
        AcademicLevelsSorter.byCode(academicLevels);
        bindFormRunning(projects, academicLevels);
    }

    private void bindFormRunning(List<Project> projects, List<AcademicLevel> academicLevels) {
        if (projects != null && academicLevels != null) {
            runningTeamForm.bind(runningTeam, projects, academicLevels);
        }
    }

    @Override
    public void onCreateSucceeded(RunningTeam runningTeam) {
    }

    @Override
    public void onUpdateSucceeded(RunningTeam runningTeam) {
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