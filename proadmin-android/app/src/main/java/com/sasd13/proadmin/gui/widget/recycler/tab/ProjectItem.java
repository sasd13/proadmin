package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

import java.util.Observable;

public class ProjectItem extends TabItem {

    private TextView textViewCode, textViewDescription;

    public ProjectItem() {
        super(R.layout.tabitem_project);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewCode = (TextView) view.findViewById(R.id.tabitem_project_textview_code);
        textViewDescription = (TextView) view.findViewById(R.id.tabitem_project_textview_description);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setCode((ProjectModel) observable);
        setDescription((ProjectModel) observable);
    }

    private void setCode(ProjectModel projectModel) {
        if (textViewCode != null) {
            textViewCode.setText(projectModel.getCode());
        }
    }

    private void setDescription(ProjectModel projectModel) {
        if (textViewDescription != null) {
            textViewDescription.setText(projectModel.getDescription());
        }
    }
}
