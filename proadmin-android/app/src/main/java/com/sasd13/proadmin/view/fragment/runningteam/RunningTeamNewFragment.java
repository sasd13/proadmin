package com.sasd13.proadmin.view.fragment.runningteam;

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
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.scope.RunningTeamScope;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.view.gui.form.RunningTeamForm;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamNewFragment extends Fragment implements Observer {

    private IRunningTeamController controller;
    private RunningTeamScope scope;
    private RunningTeamForm runningTeamForm;
    private Menu menu;

    public static RunningTeamNewFragment newInstance() {
        return new RunningTeamNewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IRunningTeamController) ((MainActivity) getActivity()).lookup(IRunningTeamController.class);
        scope = (RunningTeamScope) controller.getScope();
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
        bindFormWithRunningTeam(scope.getRunningTeam());
        bindFormWithRunnings(scope.getRunnings());
        bindFormWithTeams(scope.getTeams());
        bindFormWithAcademicLevels(scope.getAcademicLevels());
    }

    private void buildFormRunningTeam(View view) {
        runningTeamForm = new RunningTeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam(RunningTeam runningTeam) {
        runningTeamForm.bindRunningTeam(runningTeam);
    }

    private void bindFormWithRunnings(List<Running> runnings) {
        RunningsSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings);
    }

    private void bindFormWithTeams(List<Team> teams) {
        TeamsSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams);
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels) {
        AcademicLevelsSorter.byCode(academicLevels);
        runningTeamForm.bindAcademicLevels(academicLevels);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

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
            editRunningTeamWithForm();
            controller.actionCreateRunningTeam(scope.getRunningTeam());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private void editRunningTeamWithForm() throws FormException {
        RunningTeam runningTeam = scope.getRunningTeam();

        runningTeam.setRunning(runningTeamForm.getRunning());
        runningTeam.setTeam(runningTeamForm.getTeam());
        runningTeam.setAcademicLevel(runningTeamForm.getAcademicLevel());
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_runningteam));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.title_new));
    }

    @Override
    public void update(Observable observable, Object o) {
        scope = (RunningTeamScope) observable;

        bindFormWithRunnings(scope.getRunnings());
        bindFormWithTeams(scope.getTeams());
        bindFormWithAcademicLevels(scope.getAcademicLevels());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}