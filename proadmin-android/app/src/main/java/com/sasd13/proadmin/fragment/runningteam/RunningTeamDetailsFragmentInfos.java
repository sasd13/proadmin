package com.sasd13.proadmin.fragment.runningteam;

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
import com.sasd13.proadmin.controller.IRunningTeamController;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.util.wrapper.RunningTeamWrapper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RunningTeamDetailsFragmentInfos extends Fragment implements Observer {

    private IRunningTeamController controller;
    private RunningTeam runningTeam;
    private List<Running> runnings;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;
    private RunningTeamForm runningTeamForm;
    private Menu menu;

    public static RunningTeamDetailsFragmentInfos newInstance(RunningTeamWrapper runningTeamWrapper) {
        RunningTeamDetailsFragmentInfos fragment = new RunningTeamDetailsFragmentInfos();
        fragment.runningTeam = runningTeamWrapper.getRunningTeam();
        fragment.runnings = runningTeamWrapper.getRunnings();
        fragment.teams = runningTeamWrapper.getTeams();
        fragment.academicLevels = runningTeamWrapper.getAcademicLevels();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (IRunningTeamController) ((MainActivity) getActivity()).lookup(IRunningTeamController.class);
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
        buildFormTeam(view);
        bindFormWithRunningTeam();
        bindFormWithRunnings();
        bindFormWithTeams();
        bindFormWithAcademicLevels();
    }

    private void buildFormTeam(View view) {
        runningTeamForm = new RunningTeamForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.layout_rv_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, runningTeamForm.getHolder());
    }

    private void bindFormWithRunningTeam() {
        runningTeamForm.bindRunningTeam(runningTeam);
    }

    private void bindFormWithRunnings() {
        RunningsSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings, runningTeam.getRunning());
    }

    private void bindFormWithTeams() {
        TeamsSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams, runningTeam.getTeam());
    }

    private void bindFormWithAcademicLevels() {
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
                updateTeam();
                break;
            case R.id.menu_edit_action_delete:
                deleteTeam();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateTeam() {
        try {
            controller.updateRunningTeam(getRunningTeamFromForm(), runningTeam);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private RunningTeam getRunningTeamFromForm() throws FormException {
        RunningTeam runningTeamFromForm = new RunningTeam();

        runningTeamFromForm.setRunning(runningTeamForm.getRunning());
        runningTeamFromForm.setTeam(runningTeamForm.getTeam());
        runningTeamFromForm.setAcademicLevel(runningTeamForm.getAcademicLevel());

        return runningTeamFromForm;
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.deleteRunningTeams(new RunningTeam[]{runningTeam});
                    }
                });
    }

    @Override
    public void update(Observable observable, Object o) {
        RunningTeamWrapper runningTeamWrapper = (RunningTeamWrapper) observable;

        runnings = runningTeamWrapper.getRunnings();
        teams = runningTeamWrapper.getTeams();
        academicLevels = runningTeamWrapper.getAcademicLevels();

        bindFormWithRunnings();
        bindFormWithTeams();
        bindFormWithAcademicLevels();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_edit_group, false);
        }
    }
}