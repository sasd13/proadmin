package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.project.ProjectsCodesBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningTeamForm extends Form {

    private TextItemModel modelYear;
    private SpinRadioItemModel modelProject;
    private List<Project> projects;

    public RunningTeamForm(Context context) {
        super(context);

        modelYear = new TextItemModel();
        modelYear.setReadOnly(true);
        modelYear.setLabel(context.getResources().getString(R.string.label_year));
        holder.add(new RecyclerHolderPair(modelYear));

        modelProject = new SpinRadioItemModel();
        modelProject.setLabel(context.getResources().getString(R.string.label_project));
        holder.add(new RecyclerHolderPair(modelProject));
    }

    public void bind(RunningTeam runningTeam, List<Project> projects) {
        this.projects = projects;
        List<String> projectsCodes = new ProjectsCodesBuilder(projects).build();

        modelYear.setValue(String.valueOf(runningTeam.getRunning().getYear()));
        modelProject.setItems(projectsCodes.toArray(new String[projectsCodes.size()]));

        if (runningTeam.getRunning() != null && runningTeam.getRunning().getProject() != null) {
            modelProject.setValue(Finder.indexOf(runningTeam.getRunning().getProject().getCode(), projects));
        }
    }

    public int getYear() throws FormException {
        if (!StringUtils.isNumeric(modelYear.getValue())) {
            throw new FormException(context, R.string.form_runnings_message_error_year);
        }

        return Integer.parseInt(modelYear.getValue());
    }

    public Project getProject() throws FormException {
        if (modelProject.getValue() < 0) {
            throw new FormException(context, R.string.form_runnings_message_error_project);
        }

        return projects.get(modelProject.getValue());
    }
}
