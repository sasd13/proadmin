package com.sasd13.proadmin.android.view.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.bean.StudentTeam;

import java.util.Observable;

public class StudentTeamItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private StudentTeam studentTeam;

    public StudentTeamItemModel(StudentTeam studentTeam) {
        this.studentTeam = studentTeam;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.STUDENTTEAM;
    }

    @Override
    public String getLabel() {
        return studentTeam.getStudent().getIntermediary();
    }

    public String getFullName() {
        return studentTeam.getStudent().getFullName();
    }
}