package com.sasd13.proadmin.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class StudentForm extends Form {

    private TextItemModel modelNumber, modelFirstName, modelLastName, modelEmail;

    public StudentForm(Context context) {
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

    public void bind(Student student) {
        modelNumber.setValue(student.getNumber());
        modelFirstName.setValue(student.getFirstName());
        modelLastName.setValue(student.getLastName());
        modelEmail.setValue(student.getEmail());
    }

    public String getFirstName() throws FormException {
        if (StringUtils.isBlank(modelFirstName.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_firstname);
        }

        return modelFirstName.getValue().trim();
    }

    public String getLastName() throws FormException {
        if (StringUtils.isBlank(modelLastName.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_lastname);
        }

        return modelLastName.getValue().trim();
    }

    public String getEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_email);
        }

        return modelEmail.getValue().trim();
    }
}
