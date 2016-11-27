package com.sasd13.proadmin.activity.fragment.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.RunningTeamsActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.builder.running.RunningTeamFromFormBuilder;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.ws.service.RunningTeamDependencyService;
import com.sasd13.proadmin.ws.service.RunningTeamsService;

import java.util.List;

public class RunningTeamNewFragment extends Fragment implements RunningTeamsService.ManageCaller, RunningTeamDependencyService.RetrieveCaller {

    private RunningTeamsActivity parentActivity;

    private RunningTeamForm runningTeamForm;

    private Running running;
    private Team team;

    private RunningTeamsService service;
    private RunningTeamDependencyService dependencyService;

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
        service = new RunningTeamsService(this);
        dependencyService = new RunningTeamDependencyService(this);
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
        buildFormRunningTeam(view);
        bindFormWithRunningTeam();
    }

    private void buildFormRunningTeam(View view) {
        runningTeamForm = new RunningTeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam() {
        if (running != null) {
            runningTeamForm.bindRunningTeam(new DefaultRunningTeamBuilder(running).build());
        } else if (team != null) {
            runningTeamForm.bindRunningTeam(new DefaultRunningTeamBuilder(team).build());
        } else {
            runningTeamForm.bindRunningTeam(new DefaultRunningTeamBuilder().build());
        }
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
        try {
            service.create(getRunningTeamFromForm());
        } catch (FormException e) {
            displayError(e.getMessage());
        }
    }

    private RunningTeam getRunningTeamFromForm() throws FormException {
        return new RunningTeamFromFormBuilder(runningTeamForm).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteam));
        parentActivity.getSupportActionBar().setSubtitle(null);
        readDependenciesFromWS();
    }

    private void readDependenciesFromWS() {
        dependencyService.read();
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onRetrieved(List<Running> runningsFromWS, List<Team> teamsFromWS, List<AcademicLevel> academicLevelsFromWS) {
        bindRunnings(runningsFromWS);
        bindTeams(teamsFromWS);
        bindAcademicLevels(academicLevelsFromWS);
    }

    private void bindRunnings(List<Running> runningsFromWS) {
        RunningsSorter.byProjectCode(runningsFromWS);
        runningTeamForm.bindRunnings(runningsFromWS);
    }

    private void bindTeams(List<Team> teamsFromWS) {
        TeamsSorter.byNumber(teamsFromWS);
        runningTeamForm.bindTeams(teamsFromWS);
    }

    private void bindAcademicLevels(List<AcademicLevel> academicLevelsFromWS) {
        AcademicLevelsSorter.byCode(academicLevelsFromWS);
        runningTeamForm.bindAcademicLevels(academicLevelsFromWS);
    }

    @Override
    public void onCreated() {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listRunningTeams();
    }

    @Override
    public void onUpdated() {
    }

    @Override
    public void onDeleted() {
    }

    @Override
    public void onError(List<String> errors) {
        displayError(WebServiceUtils.handleErrors(getContext(), errors));
    }

    public void displayError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}