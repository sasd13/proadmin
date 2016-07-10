package com.sasd13.proadmin.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.gui.widget.recycler.MyRecyclerItemType;

import java.util.Observable;

public class ProjectItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Project project;

    public ProjectItemModel(Project project) {
        this.project = project;
    }

    @Override
    public IType getType() {
        return MyRecyclerItemType.TAB_PROJECT;
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
