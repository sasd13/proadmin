package com.sasd13.proadmin.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SettingsForm extends Form {

    private TextItemModel modelNumber, modelFirstName, modelLastName, modelEmail;

    public SettingsForm(Context context) {
        super(context);

        String title = context.getResources().getString(R.string.title_identity);

        modelNumber = new TextItemModel();
        modelNumber.setLabel(context.getResources().getString(R.string.label_number));
        modelNumber.setReadOnly(true);
        holder.add(title, new RecyclerHolderPair(modelNumber));

        modelFirstName = new TextItemModel();
        modelFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getResources().getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new TextItemModel();
        modelLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getResources().getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        title = context.getResources().getString(R.string.drawer_header_account);

        modelEmail = new TextItemModel();
        modelEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getResources().getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelEmail));
    }

    public void bindTeacher(Teacher teacher) {
        modelNumber.setValue(teacher.getNumber());
        modelFirstName.setValue(teacher.getFirstName());
        modelLastName.setValue(teacher.getLastName());
        modelEmail.setValue(teacher.getEmail());
    }

    public Teacher getEditable() throws FormException {
        validForm();

        Teacher teacher = new Teacher();

        teacher.setNumber(modelNumber.getValue());
        teacher.setFirstName(modelFirstName.getValue());
        teacher.setLastName(modelLastName.getValue());
        teacher.setEmail(modelEmail.getValue());

        return teacher;
    }

    private void validForm() throws FormException {
        validFirstName();
        validLastName();
        validEmail();
    }

    private void validFirstName() throws FormException {
        if (StringUtils.isBlank(modelFirstName.getValue())) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_firstname));
        }
    }

    private void validLastName() throws FormException {
        if (StringUtils.isBlank(modelLastName.getValue())) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_lastname));
        }
    }

    private void validEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context.getResources().getString(R.string.form_settings_message_error_email));
        }
    }

    public String getEmail() throws FormException {
        validEmail();

        return modelEmail.getValue().trim();
    }
}
