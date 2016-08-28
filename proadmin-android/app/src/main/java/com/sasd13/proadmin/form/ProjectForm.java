package com.sasd13.proadmin.form;

import android.content.Context;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumAcademicLevelRes;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class ProjectForm extends Form {

    private TextItemModel modelTitle, modelAcademicLevel, modelCode, modelDescription;

    public ProjectForm(Context context) {
        super(context);

        modelTitle = new TextItemModel();
        modelTitle.setReadOnly(true);
        modelTitle.setLabel(context.getResources().getString(R.string.label_title));
        holder.add(new RecyclerHolderPair(modelTitle));

        modelAcademicLevel = new TextItemModel();
        modelAcademicLevel.setReadOnly(true);
        modelAcademicLevel.setLabel(context.getResources().getString(R.string.label_academiclevel));
        holder.add(new RecyclerHolderPair(modelAcademicLevel));

        modelCode = new TextItemModel();
        modelCode.setReadOnly(true);
        modelCode.setLabel(context.getResources().getString(R.string.label_code));
        holder.add(new RecyclerHolderPair(modelCode));

        modelDescription = new TextItemModel();
        modelDescription.setReadOnly(true);
        modelDescription.setLabel(context.getResources().getString(R.string.label_description));
        holder.add(new RecyclerHolderPair(modelDescription));
    }

    public void bind(Project project) {
        modelTitle.setValue(project.getTitle());

        EnumAcademicLevelRes academicLevelRes = EnumAcademicLevelRes.find(project.getAcademicLevel());
        modelAcademicLevel.setValue(context.getResources().getString(academicLevelRes.getStringRes()));

        modelCode.setValue(project.getCode());
        modelDescription.setValue(project.getDescription());
    }
}