package com.sasd13.proadmin.android.component.runningteam.view;

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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.runningteam.scope.RunningTeamScope;
import com.sasd13.proadmin.android.gui.form.RunningTeamForm;
import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.util.sorter.AcademicLevelSorter;
import com.sasd13.proadmin.android.util.sorter.RunningSorter;
import com.sasd13.proadmin.android.util.sorter.TeamSorter;

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

        controller = (IRunningTeamController) ((MainActivity) getActivity()).lookup(IRunningTeamController.class);
        scope = (RunningTeamScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_rv, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(view);
        buildFormRunningTeam(view);
        bindFormWithRunningTeam(scope.getRunningTeam());
        bindFormWithDependencies();
    }

    private void buildFormRunningTeam(View view) {
        runningTeamForm = new RunningTeamForm(getContext(), false);
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam(RunningTeam runningTeam) {
        runningTeamForm.bindRunningTeam(runningTeam);
    }

    private void bindFormWithDependencies() {
        bindFormWithRunnings(scope.getRunnings());
        bindFormWithTeams(scope.getTeams());
        bindFormWithAcademicLevels(scope.getAcademicLevels());
    }

    private void bindFormWithRunnings(List<Running> runnings) {
        RunningSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings);
    }

    private void bindFormWithTeams(List<Team> teams) {
        TeamSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams);
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels) {
        AcademicLevelSorter.byCode(academicLevels);
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
                createRunningTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createRunningTeam() {
        try {
            controller.actionCreateRunningTeam(getEditedRunningTeamWithForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private RunningTeam getEditedRunningTeamWithForm() throws FormException {
        RunningTeam runningTeam = scope.getRunningTeam();

        runningTeam.setRunning(runningTeamForm.getRunning());
        runningTeam.setTeam(runningTeamForm.getTeam());
        runningTeam.setAcademicLevel(runningTeamForm.getAcademicLevel());

        return runningTeam;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_runningteam));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.title_new));
    }

    @Override
    public void update(Observable observable, Object o) {
        bindFormWithRunningTeam(scope.getRunningTeam());
        bindFormWithDependencies();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}