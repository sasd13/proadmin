package com.sasd13.proadmin.activity.fragment.runningteam;

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
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.RunningTeamsActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.service.running.RunningTeamManageService;
import com.sasd13.proadmin.util.caller.IRunningTeamReadServiceCaller;
import com.sasd13.proadmin.util.caller.RunningTeamAcademicLevelReadServiceCaller;
import com.sasd13.proadmin.util.caller.RunningTeamProjectReadServiceCaller;
import com.sasd13.proadmin.util.caller.RunningTeamTeamReadServiceCaller;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.project.ProjectsSorter;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.List;

public class RunningTeamNewFragment extends Fragment implements IManageServiceCaller<RunningTeam>, IRunningTeamReadServiceCaller {

    private RunningTeamsActivity parentActivity;

    private RunningTeamForm runningTeamForm;

    private Running running;
    private Team team;

    private RunningTeamManageService runningTeamManageService;
    private RunningTeamProjectReadServiceCaller projectReadServiceCaller;
    private RunningTeamAcademicLevelReadServiceCaller academicLevelReadServiceCaller;
    private RunningTeamTeamReadServiceCaller teamReadServiceCaller;

    public static RunningTeamNewFragment newInstance() {
        return new RunningTeamNewFragment();
    }

    public static RunningTeamNewFragment newInstance(Running running) {
        RunningTeamNewFragment fragment = newInstance();
        fragment.running = running;

        return fragment;
    }

    public static RunningTeamNewFragment newInstance(Team team) {
        RunningTeamNewFragment fragment = newInstance();
        fragment.team = team;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (RunningTeamsActivity) getActivity();
        runningTeamManageService = new RunningTeamManageService(this);
        projectReadServiceCaller = new RunningTeamProjectReadServiceCaller(this);
        academicLevelReadServiceCaller = new RunningTeamAcademicLevelReadServiceCaller(this);
        teamReadServiceCaller = new RunningTeamTeamReadServiceCaller(this);
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

        RecyclerHelper.addAll(form, runningTeamForm.getHolder());
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
                createTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createTeam() {
        runningTeamManageService.create(runningTeamForm, running);
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteam));
        readProjectsFromWS();
        readAcademicLevelsFromWS();
        readTeamsFromWS();
    }

    private void readProjectsFromWS() {
        projectReadServiceCaller.readProjectsFromWS();
    }

    private void readAcademicLevelsFromWS() {
        academicLevelReadServiceCaller.readAcademicLevelsFromWS();
    }

    private void readTeamsFromWS() {
        teamReadServiceCaller.readTeamsFromWS();
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onReadProjectsSucceeded(IReadWrapper<Project> projectReadWrapper) {
        ProjectsSorter.byCode(projectReadWrapper.getWrapped());
        bindFormWithProjects(projectReadWrapper.getWrapped());
    }

    private void bindFormWithProjects(List<Project> projects) {
        if (running != null) {
            runningTeamForm.bindProjects(projects, running.getProject());
        } else {
            runningTeamForm.bindProjects(projects);
        }
    }

    @Override
    public void onReadAcademicLevelsSucceeded(IReadWrapper<AcademicLevel> academicLevelReadWrapper) {
        AcademicLevelsSorter.byCode(academicLevelReadWrapper.getWrapped());
        bindFormWithAcademicLevels(academicLevelReadWrapper.getWrapped());
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels) {
        runningTeamForm.bindAcademicLevels(academicLevels);
    }

    @Override
    public void onReadTeamsSucceeded(IReadWrapper<Team> teamReadWrapper) {
        TeamsSorter.byNumber(teamReadWrapper.getWrapped());
        bindFormWithTeams(teamReadWrapper.getWrapped());
    }

    private void bindFormWithTeams(List<Team> teams) {
        if (team != null) {
            runningTeamForm.bindTeams(teams, team);
        } else {
            runningTeamForm.bindTeams(teams);
        }
    }

    @Override
    public void onCreateSucceeded(RunningTeam runningTeam) {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunningTeams();
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