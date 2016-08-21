package com.sasd13.proadmin.form;

import android.content.Context;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.project.ProjectsCodesBuilder;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningForm extends Form {

    private TextItemModel modelYear;
    private SpinRadioItemModel modelProject;
    private List<Project> projects;
    private List<String> projectsCodes;

    public RunningForm(Context context, List<Project> projects) {
        super(context);

        this.projects = projects;
        projectsCodes = new ProjectsCodesBuilder(projects).build();

        modelYear = new TextItemModel();
        modelYear.setReadOnly(true);
        modelYear.setLabel(context.getResources().getString(R.string.label_year));
        holder.add(new RecyclerHolderPair(modelYear));

        modelProject = new SpinRadioItemModel();
        modelProject.setLabel(context.getResources().getString(R.string.label_project));
        modelProject.setItems(projectsCodes.toArray(new String[projectsCodes.size()]));
        holder.add(new RecyclerHolderPair(modelProject));
    }

    public void bind(Running running) {
        modelYear.setValue(String.valueOf(running.getYear()));
        modelProject.setValue(projectsCodes.indexOf(running.getProject().getCode()));
    }

    public Running getEditable() {
        Running running = new Running();

        running.setYear(Integer.parseInt(modelYear.getValue()));
        running.setProject(projects.get(modelProject.getValue()));

        return running;
    }
}
