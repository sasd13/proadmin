package com.sasd13.proadmin.android.view.fragment.runningteam;

import android.content.DialogInterface;
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
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.scope.RunningTeamScope;
import com.sasd13.proadmin.android.util.sorter.AcademicLevelSorter;
import com.sasd13.proadmin.android.util.sorter.RunningSorter;
import com.sasd13.proadmin.android.util.sorter.TeamSorter;
import com.sasd13.proadmin.android.view.IRunningTeamController;
import com.sasd13.proadmin.android.view.gui.form.RunningTeamForm;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamDetailsFragmentInfos extends Fragment implements Observer {

    private IRunningTeamController controller;
    private RunningTeamScope scope;
    private RunningTeamForm runningTeamForm;
    private Menu menu;

    public static RunningTeamDetailsFragmentInfos newInstance() {
        return new RunningTeamDetailsFragmentInfos();
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
        runningTeamForm = new RunningTeamForm(getContext(), true);
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam(RunningTeam runningTeam) {
        runningTeamForm.bindRunningTeam(runningTeam);
    }

    private void bindFormWithDependencies() {
        bindFormWithRunnings(scope.getRunnings(), scope.getRunningTeam().getRunning());
        bindFormWithTeams(scope.getTeams(), scope.getRunningTeam().getTeam());
        bindFormWithAcademicLevels(scope.getAcademicLevels(), scope.getRunningTeam().getAcademicLevel());
    }

    private void bindFormWithRunnings(List<Running> runnings, Running running) {
        RunningSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings, running);
    }

    private void bindFormWithTeams(List<Team> teams, Team team) {
        TeamSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams, team);
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels, AcademicLevel academicLevel) {
        AcademicLevelSorter.byCode(academicLevels);
        runningTeamForm.bindAcademicLevels(academicLevels, academicLevel);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_action_save:
                updateRunningTeam();
                break;
            case R.id.menu_edit_action_delete:
                deleteRunningTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateRunningTeam() {
        try {
            controller.actionUpdateRunningTeam(getEditedRunningTeamFromForm());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private RunningTeam getEditedRunningTeamFromForm() throws FormException {
        RunningTeam runningTeam = scope.getRunningTeam();

        runningTeam.setRunning(runningTeamForm.getRunning());
        runningTeam.setTeam(runningTeamForm.getTeam());
        runningTeam.setAcademicLevel(runningTeamForm.getAcademicLevel());

        return runningTeam;
    }

    private void deleteRunningTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getString(R.string.message_delete),
                getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.actionRemoveRunningTeam(scope.getRunningTeam());
                    }
                });
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