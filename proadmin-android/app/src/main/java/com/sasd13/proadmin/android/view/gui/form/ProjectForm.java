package com.sasd13.proadmin.android.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class ProjectForm extends Form {

    private TextItemModel modelTitle, modelCode, modelDescription;

    public ProjectForm(Context context) {
        super(context);

        modelCode = new TextItemModel();
        modelCode.setReadOnly(true);
        modelCode.setLabel(context.getString(R.string.label_code));
        holder.add(new RecyclerHolderPair(modelCode));

        modelTitle = new TextItemModel();
        modelTitle.setReadOnly(true);
        modelTitle.setLabel(context.getString(R.string.label_title));
        holder.add(new RecyclerHolderPair(modelTitle));

        modelDescription = new TextItemModel();
        modelDescription.setReadOnly(true);
        modelDescription.setLabel(context.getString(R.string.label_description));
        holder.add(new RecyclerHolderPair(modelDescription));
    }

    public void bind(Project project) {
        modelCode.setValue(project.getCode());
        modelTitle.setValue(project.getTitle());
        modelDescription.setValue(project.getDescription());
    }
}
