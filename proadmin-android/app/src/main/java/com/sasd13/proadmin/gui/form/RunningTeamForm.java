package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.AcademicLevelsCodesBuilder;
import com.sasd13.proadmin.util.builder.project.ProjectsCodesBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningTeamForm extends Form {

    private TextItemModel modelNumber, modelYear;
    private SpinRadioItemModel modelProject, modelAcademicLevel;
    private List<Project> projects;
    private List<AcademicLevel> academicLevels;

    public RunningTeamForm(Context context) {
        super(context);

        modelNumber = new TextItemModel();
        modelNumber.setReadOnly(true);
        modelNumber.setLabel(context.getResources().getString(R.string.label_number));
        holder.add(new RecyclerHolderPair(modelNumber));

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
    }

    public void bind(RunningTeam runningTeam, List<Project> projects, List<AcademicLevel> academicLevels) {
        modelYear.setValue(String.valueOf(runningTeam.getRunning().getYear()));

        setProjects(runningTeam, projects);
        setAcademicLevels(runningTeam, academicLevels);
    }

    private void setProjects(RunningTeam runningTeam, List<Project> projects) {
        this.projects = projects;
        List<String> projectsCodes = new ProjectsCodesBuilder(projects).build();

        modelProject.setItems(projectsCodes.toArray(new String[projectsCodes.size()]));

        if (runningTeam.getRunning() != null && runningTeam.getRunning().getProject() != null) {
            modelProject.setValue(Finder.indexOfProject(runningTeam.getRunning().getProject().getCode(), projects));
        }
    }

    private void setAcademicLevels(RunningTeam runningTeam, List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;
        List<String> academicLevelsCodes = new AcademicLevelsCodesBuilder(academicLevels).build();

        modelProject.setItems(academicLevelsCodes.toArray(new String[academicLevelsCodes.size()]));

        if (runningTeam.getAcademicLevel() != null) {
            modelProject.setValue(Finder.indexOfAcademicLevel(runningTeam.getAcademicLevel().getCode(), academicLevels));
        }
    }

    public String getNumber() throws FormException {
        if (!StringUtils.isBlank(modelNumber.getValue())) {
            throw new FormException(context, R.string.form_runningteams_message_error_number);
        }

        return modelNumber.getValue();
    }

    public int getYear() throws FormException {
        if (!StringUtils.isNumeric(modelYear.getValue())) {
            throw new FormException(context, R.string.form_runnings_message_error_year);
        }

        return Integer.parseInt(modelYear.getValue());
    }

    public Project getProject() throws FormException {
        if (modelProject.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteams_message_error_project);
        }

        return projects.get(modelProject.getValue());
    }

    public AcademicLevel getAcademicLevel() throws FormException {
        if (modelAcademicLevel.getValue() < 0) {
            throw new FormException(context, R.string.form_runningteams_message_error_academiclevel);
        }

        return academicLevels.get(modelAcademicLevel.getValue());
    }
}
