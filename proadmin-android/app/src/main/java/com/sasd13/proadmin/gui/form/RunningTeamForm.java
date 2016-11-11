package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.AcademicLevelsCodesBuilder;
import com.sasd13.proadmin.util.builder.member.TeamsNumbersBuilder;
import com.sasd13.proadmin.util.builder.project.ProjectsCodesBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningTeamForm extends Form {

    private TextItemModel modelYear;
    private SpinRadioItemModel modelProject, modelAcademicLevel, modelTeam;
    private List<Project> projects;
    private List<AcademicLevel> academicLevels;
    private List<Team> teams;

    public RunningTeamForm(Context context) {
        super(context);

        modelYear = new TextItemModel();
        modelYear.setReadOnly(true);
        modelYear.setLabel(context.getResources().getString(R.string.label_year));
        holder.add(new RecyclerHolderPair(modelYear));

        modelProject = new SpinRadioItemModel();
        modelProject.setLabel(context.getResources().getString(R.string.label_project));
        holder.add(new RecyclerHolderPair(modelProject));

        modelAcademicLevel = new SpinRadioItemModel();
        modelAcademicLevel.setLabel(context.getResources().getString(R.string.label_academiclevel));
        holder.add(new RecyclerHolderPair(modelAcademicLevel));

        modelTeam = new SpinRadioItemModel();
        modelTeam.setLabel(context.getResources().getString(R.string.label_team));
        holder.add(new RecyclerHolderPair(modelTeam));
    }

    public void bindRunningTeam(RunningTeam runningTeam) {
        modelYear.setValue(String.valueOf(runningTeam.getRunning().getYear()));
    }

    public void bindProjects(List<Project> projectsToBind, Project project) {
        projects = projectsToBind;
        List<String> projectsCodes = new ProjectsCodesBuilder(projects).build();

        modelProject.setItems(projectsCodes.toArray(new String[projectsCodes.size()]));

        if (project != null) {
            modelProject.setValue(Finder.indexOfProject(project.getCode(), projects));
        }
    }

    public void bindAcademicLevels(List<AcademicLevel> academicLevelsToBind, AcademicLevel academicLevel) {
        academicLevels = academicLevelsToBind;
        List<String> academicLevelsCodes = new AcademicLevelsCodesBuilder(academicLevels).build();

        modelAcademicLevel.setItems(academicLevelsCodes.toArray(new String[academicLevelsCodes.size()]));

        if (academicLevel != null) {
            modelAcademicLevel.setValue(Finder.indexOfAcademicLevel(academicLevel.getCode(), academicLevels));
        }
    }

    public void bindTeams(List<Team> teamsToBind, Team team) {
        teams = teamsToBind;
        List<String> teamsCodes = new TeamsNumbersBuilder(teams).build();

        modelTeam.setItems(teamsCodes.toArray(new String[teamsCodes.size()]));

        if (team != null) {
            modelTeam.setValue(Finder.indexOfTeam(team.getNumber(), teams));
        }
    }

    public int getYear() throws FormException {
        if (!StringUtils.isNumeric(modelYear.getValue())) {
            throw new FormException(context, R.string.form_runningteam_message_error_year);
        }

        return Integer.parseInt(modelYear.getValue());
    }

    public Project getProject() throws FormException {
        if (modelProject.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteam_message_error_project);
        }

        return projects.get(modelProject.getValue());
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
