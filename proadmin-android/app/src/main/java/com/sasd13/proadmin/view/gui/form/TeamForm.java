package com.sasd13.proadmin.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class TeamForm extends Form {

    private TextItemModel modelNumber, modelName;

    public TeamForm(Context context) {
        super(context);

        modelNumber = new TextItemModel();
        modelNumber.setLabel(context.getString(R.string.label_number));
        holder.add(new RecyclerHolderPair(modelNumber));

        modelName = new TextItemModel();
        modelName.setLabel(context.getString(R.string.label_name));
        holder.add(new RecyclerHolderPair(modelName));
    }

    public void bindTeam(Team team) {
        modelNumber.setValue(team.getNumber());
        modelName.setValue(team.getName());
    }

    public String getNumber() throws FormException {
        if (StringUtils.isBlank(modelNumber.getValue())) {
            throw new FormException(context, R.string.form_team_message_error_number);
        }

        return modelNumber.getValue().trim();
    }

    public String getName() throws FormException {
        if (StringUtils.isBlank(modelName.getValue())) {
            throw new FormException(context, R.string.form_team_message_error_name);
        }

        return modelName.getValue().trim();
    }
}
