package com.sasd13.proadmin.view.runningteam;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.builder.running.RunningTeamFromFormBuilder;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.util.wrapper.RunningTeamWrapper;
import com.sasd13.proadmin.view.IRunningTeamController;

import java.util.List;

public class RunningTeamNewFragment extends Fragment {

    private IRunningTeamController controller;
    private List<Running> runnings;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;
    private RunningTeamForm runningTeamForm;
    private Running running;
    private Team team;

    public static RunningTeamNewFragment newInstance(IRunningTeamController controller, RunningTeamWrapper runningTeamWrapper) {
        RunningTeamNewFragment fragment = new RunningTeamNewFragment();
        fragment.controller = controller;
        fragment.runnings = runningTeamWrapper.getRunnings();
        fragment.teams = runningTeamWrapper.getTeams();
        fragment.academicLevels = runningTeamWrapper.getAcademicLevels();

        return fragment;
    }

    public void setRunning(Running running) {
        this.running = running;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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
        bindFormWithRunnings();
        bindFormWithTeams();
        bindFormWithAcademicLevels();
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

    private void bindFormWithRunnings() {
        RunningsSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings);
    }

    private void bindFormWithTeams() {
        TeamsSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams);
    }

    private void bindFormWithAcademicLevels() {
        AcademicLevelsSorter.byCode(academicLevels);
        runningTeamForm.bindAcademicLevels(academicLevels);
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
            RunningTeam runningTeamFromForm = new RunningTeamFromFormBuilder(runningTeamForm).build();

            controller.createRunningTeam(runningTeamFromForm);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }
}