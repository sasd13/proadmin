package com.sasd13.proadmin.android.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.util.IndexFinder;
import com.sasd13.proadmin.android.util.builder.AcademicLevelsCodesBuilder;
import com.sasd13.proadmin.android.util.builder.RunningsLabelsFromRunningsBuilder;
import com.sasd13.proadmin.android.util.builder.TeamsNumbersBuilder;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningTeamForm extends Form {

    private SpinRadioItemModel modelRunning, modelTeam, modelAcademicLevel;
    private List<Running> runnings;
    private List<AcademicLevel> academicLevels;
    private List<Team> teams;

    public RunningTeamForm(Context context, boolean inModeEdit) {
        super(context);

        modelRunning = new SpinRadioItemModel();
        modelRunning.setLabel(context.getString(R.string.label_running));
        modelRunning.setReadOnly(inModeEdit);
        holder.add(new RecyclerHolderPair(modelRunning));

        modelTeam = new SpinRadioItemModel();
        modelTeam.setLabel(context.getString(R.string.label_team));
        modelTeam.setReadOnly(inModeEdit);
        holder.add(new RecyclerHolderPair(modelTeam));

        modelAcademicLevel = new SpinRadioItemModel();
        modelAcademicLevel.setLabel(context.getString(R.string.label_academiclevel));
        modelAcademicLevel.setReadOnly(inModeEdit);
        holder.add(new RecyclerHolderPair(modelAcademicLevel));
    }

    public void bindRunningTeam(RunningTeam runningTeam) {
        //Do nothing
    }

    public List<String> bindRunnings(List<Running> runningsToBind) {
        runnings = runningsToBind;
        List<String> runningsLabels = new RunningsLabelsFromRunningsBuilder(runnings).build();

        modelRunning.setItems(runningsLabels.toArray(new String[runningsLabels.size()]));

        return runningsLabels;
    }

    public List<String> bindRunnings(List<Running> runningsToBind, Running running) {
        List<String> runningsLabels = bindRunnings(runningsToBind);

        modelRunning.setValue(runningsToBind.indexOf(running));

        return runningsLabels;
    }

    public void bindTeams(List<Team> teamsToBind) {
        teams = teamsToBind;
        List<String> teamsCodes = new TeamsNumbersBuilder(teams).build();

        modelTeam.setItems(teamsCodes.toArray(new String[teamsCodes.size()]));
    }

    public void bindTeams(List<Team> teamsToBind, Team team) {
        bindTeams(teamsToBind);

        modelTeam.setValue(IndexFinder.indexOfTeam(team, teams));
    }

    public void bindAcademicLevels(List<AcademicLevel> academicLevelsToBind) {
        academicLevels = academicLevelsToBind;
        List<String> academicLevelsCodes = new AcademicLevelsCodesBuilder(academicLevels).build();

        modelAcademicLevel.setItems(academicLevelsCodes.toArray(new String[academicLevelsCodes.size()]));
    }

    public void bindAcademicLevels(List<AcademicLevel> academicLevelsToBind, AcademicLevel academicLevel) {
        bindAcademicLevels(academicLevelsToBind);

        modelAcademicLevel.setValue(IndexFinder.indexOfAcademicLevel(academicLevel, academicLevels));
    }

    public Running getRunning() throws FormException {
        if (modelRunning.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_project);
        }

        return runnings.get(modelRunning.getValue());
    }

    public Team getTeam() throws FormException {
        if (modelTeam.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_team);
        }

        return teams.get(modelTeam.getValue());
    }

    public AcademicLevel getAcademicLevel() throws FormException {
        if (modelAcademicLevel.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_academiclevel);
        }

        return academicLevels.get(modelAcademicLevel.getValue());
    }
}
