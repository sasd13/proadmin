package com.sasd13.proadmin.android.view.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.android.R;

import java.util.Observable;

public class StudentTeamItem extends TabItem {

    private TextView textViewStudentFullName;

    public StudentTeamItem() {
        super(R.layout.tabitem_studentteam);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewStudentFullName = (TextView) view.findViewById(R.id.tabitem_studentteam_textview_student_fullname);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setStudentFullName((StudentTeamItemModel) observable);
    }

    private void setStudentFullName(StudentTeamItemModel studentTeamItemModel) {
        if (textViewStudentFullName != null) {
            textViewStudentFullName.setText(studentTeamItemModel.getFullName());
        }
    }
}