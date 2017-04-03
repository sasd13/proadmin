package com.sasd13.proadmin.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.AcademicLevelsCodesBuilder;
import com.sasd13.proadmin.util.builder.member.TeamsNumbersBuilder;
import com.sasd13.proadmin.util.builder.running.RunningsProjectsCodesBuilder;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningTeamForm extends Form {

    private TextItemModel modelYear;
    private SpinRadioItemModel modelRunning, modelAcademicLevel, modelTeam;
    private List<Running> runnings;
    private List<AcademicLevel> academicLevels;
    private List<Team> teams;

    public RunningTeamForm(Context context) {
        super(context);

        modelYear = new TextItemModel();
        modelYear.setReadOnly(true);
        modelYear.setLabel(context.getString(R.string.label_year));
        holder.add(new RecyclerHolderPair(modelYear));

        modelRunning = new SpinRadioItemModel();
        modelRunning.setLabel(context.getString(R.string.label_project));
        holder.add(new RecyclerHolderPair(modelRunning));

        modelAcademicLevel = new SpinRadioItemModel();
        modelAcademicLevel.setLabel(context.getString(R.string.label_academiclevel));
        holder.add(new RecyclerHolderPair(modelAcademicLevel));

        modelTeam = new SpinRadioItemModel();
        modelTeam.setLabel(context.getString(R.string.label_team));
        holder.add(new RecyclerHolderPair(modelTeam));
    }

    public void bindRunningTeam(RunningTeam runningTeam) {
        modelYear.setValue(String.valueOf(runningTeam.getRunning().getYear()));
    }

    public void bindRunnings(List<Running> runningsToBind) {
        runnings = runningsToBind;
        List<String> projectsCodes = new RunningsProjectsCodesBuilder(runnings).build();

        modelRunning.setItems(projectsCodes.toArray(new String[projectsCodes.size()]));
    }

    public void bindRunnings(List<Running> runningsToBind, Running running) {
        bindRunnings(runningsToBind);

        modelRunning.setValue(Finder.indexOfProjectInRunnings(running.getProject().getCode(), runningsToBind));
    }

    public void bindAcademicLevels(List<AcademicLevel> academicLevelsToBind) {
        academicLevels = academicLevelsToBind;
        List<String> academicLevelsCodes = new AcademicLevelsCodesBuilder(academicLevels).build();

        modelAcademicLevel.setItems(academicLevelsCodes.toArray(new String[academicLevelsCodes.size()]));
    }

    public void bindAcademicLevels(List<AcademicLevel> academicLevelsToBind, AcademicLevel academicLevel) {
        bindAcademicLevels(academicLevelsToBind);

        modelAcademicLevel.setValue(Finder.indexOfAcademicLevel(academicLevel.getCode(), academicLevels));
    }

    public void bindTeams(List<Team> teamsToBind) {
        teams = teamsToBind;
        List<String> teamsCodes = new TeamsNumbersBuilder(teams).build();

        modelTeam.setItems(teamsCodes.toArray(new String[teamsCodes.size()]));
    }

    public void bindTeams(List<Team> teamsToBind, Team team) {
        bindTeams(teamsToBind);

        modelTeam.setValue(Finder.indexOfTeam(team.getNumber(), teams));
    }

    public Running getRunning() throws FormException {
        if (modelRunning.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_project);
        }

        return runnings.get(modelRunning.getValue());
    }

    public AcademicLevel getAcademicLevel() throws FormException {
        if (modelAcademicLevel.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_academiclevel);
        }

        return academicLevels.get(modelAcademicLevel.getValue());
    }

    public Team getTeam() throws FormException {
        if (modelTeam.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_team);
        }

        return teams.get(modelTeam.getValue());
    }
}
