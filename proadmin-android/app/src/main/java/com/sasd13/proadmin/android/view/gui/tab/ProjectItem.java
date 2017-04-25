package com.sasd13.proadmin.android.view.gui.tab;

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

        setCode((ProjectItemModel) observable);
        setDescription((ProjectItemModel) observable);
    }

    private void setCode(ProjectItemModel projectItemModel) {
        if (textViewCode != null) {
            textViewCode.setText(projectItemModel.getCode());
        }
    }

    private void setDescription(ProjectItemModel projectItemModel) {
        if (textViewDescription != null) {
            textViewDescription.setText(projectItemModel.getDescription());
        }
    }
}
