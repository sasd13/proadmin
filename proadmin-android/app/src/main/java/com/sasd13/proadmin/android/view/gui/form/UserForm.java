package com.sasd13.proadmin.android.view.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.bean.User;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class UserForm extends Form {

    private TextItemModel modelIntermediary, modelEmail;

    public UserForm(Context context) {
        super(context);

        String title = context.getString(R.string.title_identity);

        modelIntermediary = new TextItemModel();
        modelIntermediary.setLabel(context.getString(R.string.label_number));
        modelIntermediary.setReadOnly(true);
        holder.add(title, new RecyclerHolderPair(modelIntermediary));

        title = context.getString(R.string.drawer_header_account);

        modelEmail = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        modelEmail.setReadOnly(true);
        holder.add(title, new RecyclerHolderPair(modelEmail));
    }

    public void bindUser(User user) {
        if (user != null) {
            modelIntermediary.setValue(user.getIntermediary());
            modelEmail.setValue(user.getEmail());
        }
    }

    public String getNumber() throws FormException {
        if (StringUtils.isBlank(modelIntermediary.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_number);
        }

        return modelIntermediary.getValue().trim();
    }

    public String getEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_email);
        }

        return modelEmail.getValue().trim();
    }
}
