package com.sasd13.proadmin.android.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.bean.Running;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningForm extends Form {

    private TextItemModel modelYear, modelProject;

    public RunningForm(Context context) {
        super(context);

        modelYear = new TextItemModel();
        modelYear.setReadOnly(true);
        modelYear.setLabel(context.getString(R.string.label_year));
        holder.add(new RecyclerHolderPair(modelYear));

        modelProject = new TextItemModel();
        modelProject.setReadOnly(true);
        modelProject.setLabel(context.getString(R.string.label_project));
        holder.add(new RecyclerHolderPair(modelProject));
    }

    public void bindRunning(Running running) {
        modelYear.setValue(String.valueOf(running.getYear()));
        modelProject.setValue(running.getProject().getCode());
    }

    public int getYear() throws FormException {
        if (!StringUtils.isNumeric(modelYear.getValue())) {
            throw new FormException(context, R.string.form_running_message_error_year);
        }

        return Integer.parseInt(modelYear.getValue());
    }
}
