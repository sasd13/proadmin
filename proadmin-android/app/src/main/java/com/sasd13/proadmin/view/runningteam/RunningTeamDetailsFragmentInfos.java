package com.sasd13.proadmin.view.runningteam;

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
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.builder.running.RunningTeamFromFormBuilder;
import com.sasd13.proadmin.util.sorter.AcademicLevelsSorter;
import com.sasd13.proadmin.util.sorter.member.TeamsSorter;
import com.sasd13.proadmin.util.sorter.running.RunningsSorter;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;

import java.util.List;

public class RunningTeamDetailsFragmentInfos extends Fragment {

    private IRunningTeamController controller;
    private RunningTeam runningTeam;
    private RunningTeamForm runningTeamForm;

    public static RunningTeamDetailsFragmentInfos newInstance(IRunningTeamController controller, RunningTeam runningTeam) {
        RunningTeamDetailsFragmentInfos fragment = new RunningTeamDetailsFragmentInfos();
        fragment.controller = controller;
        fragment.runningTeam = runningTeam;

        return fragment;
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
        buildFormTeam(view);
        bindFormWithRunningTeam();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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
            RunningTeam runningTeamFromForm = new RunningTeamFromFormBuilder(runningTeamForm).build();

            controller.updateRunningTeam(runningTeamFromForm, runningTeam);
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private void deleteTeam() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.deleteRunningTeam(runningTeam);
                    }
                });
    }

    public void setDependencyWrapper(RunningTeamDependencyWrapper dependencyWrapper) {
        bindFormWithRunnings(dependencyWrapper.getRunnings());
        bindFormWithTeams(dependencyWrapper.getTeams());
        bindFormWithAcademicLevels(dependencyWrapper.getAcademicLevels());
    }

    private void bindFormWithRunnings(List<Running> runnings) {
        RunningsSorter.byProjectCode(runnings);
        runningTeamForm.bindRunnings(runnings, runningTeam.getRunning());
    }

    private void bindFormWithTeams(List<Team> teams) {
        TeamsSorter.byNumber(teams);
        runningTeamForm.bindTeams(teams, runningTeam.getTeam());
    }

    private void bindFormWithAcademicLevels(List<AcademicLevel> academicLevels) {
        AcademicLevelsSorter.byCode(academicLevels);
        runningTeamForm.bindAcademicLevels(academicLevels, runningTeam.getAcademicLevel());
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_runningteam));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(null);
    }
}