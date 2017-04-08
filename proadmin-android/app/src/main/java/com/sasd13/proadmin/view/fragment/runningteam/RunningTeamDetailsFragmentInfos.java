package com.sasd13.proadmin.view.fragment.runningteam;

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
        runningTeamForm = new RunningTeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam(RunningTeam runningTeam) {
        runningTeamForm.bindRunningTeam(runningTeam);
    }

    private void bindFormWithDependencies() {
        bindFormWithRunnings(scope.getRunnings(), scope.getRunningTeam());
        bindFormWithTeams(scope.getTeams(), scope.getRunningTeam());
        bindFormWithAcademicLevels(scope.getAcademicLevels(), scope.getRunningTeam());
    }

    private void bindFormWithRunnings(List<Running> runnings, RunningTeam runningTeam) {
        RunningsSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings, runningTeam.getRunning());
    }

    private void bindFormWithTeams(List<Team> teams, RunningTeam runningTeam) {
        TeamsSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams, runningTeam.getTeam());
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels, RunningTeam runningTeam) {
        AcademicLevelsSorter.byCode(academicLevels);
        runningTeamForm.bindAcademicLevels(academicLevels, runningTeam.getAcademicLevel());
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
            controller.actionUpdateRunningTeam(getRunningTeamFromForm(), scope.getRunningTeam());
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private RunningTeam getRunningTeamFromForm() throws FormException {
        RunningTeam runningTeamFromForm = new RunningTeam();

        runningTeamFromForm.setRunning(runningTeamForm.getRunning());
        runningTeamFromForm.setTeam(runningTeamForm.getTeam());
        runningTeamFromForm.setAcademicLevel(runningTeamForm.getAcademicLevel());

        return runningTeamFromForm;
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
        scope = (RunningTeamScope) observable;

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