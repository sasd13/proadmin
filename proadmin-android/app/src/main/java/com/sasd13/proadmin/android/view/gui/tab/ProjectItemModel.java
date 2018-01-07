package com.sasd13.proadmin.android.view.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.model.Project;

import java.util.Observable;

public class ProjectItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Project project;

    public ProjectItemModel(Project project) {
        this.project = project;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.PROJECT;
    }

    @Override
    public String getLabel() {
        return project.getTitle();
    }

    public String getCode() {
        return project.getCode();
    }

    public String getDescription() {
        return project.getDescription();
    }
}
