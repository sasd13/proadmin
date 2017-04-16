package com.sasd13.proadmin.view.gui.form;

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

    private boolean inModeEdit;
    private TextItemModel modelNumber, modelFirstName, modelLastName, modelEmail;

    public StudentForm(Context context, boolean inModeEdit) {
        super(context);

        this.inModeEdit = inModeEdit;
        String title = context.getString(R.string.title_identity);

        if (inModeEdit) {
            modelNumber = new TextItemModel();
            modelNumber.setLabel(context.getString(R.string.label_number));
            holder.add(title, new RecyclerHolderPair(modelNumber));
        }

        modelFirstName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelFirstName.setLabel(context.getString(R.string.label_firstname));
        modelFirstName.setHint(modelFirstName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelFirstName));

        modelLastName = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        modelLastName.setLabel(context.getString(R.string.label_lastname));
        modelLastName.setHint(modelLastName.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelLastName));

        modelEmail = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        holder.add(title, new RecyclerHolderPair(modelEmail));
    }

    public void bindStudent(Student student) {
        if (inModeEdit) {
            modelNumber.setValue(student.getNumber());
        }

        modelFirstName.setValue(student.getFirstName());
        modelLastName.setValue(student.getLastName());
        modelEmail.setValue(student.getEmail());
    }

    public String getNumber() throws FormException {
        if (!inModeEdit) {
            throw new FormException(context, R.string.form_message_error);
        }

        if (StringUtils.isBlank(modelNumber.getValue())) {
            throw new FormException(context, R.string.form_student_message_error_number);
        }

        return modelNumber.getValue().trim();
    }

    public String getFirstName() throws FormException {
        if (StringUtils.isBlank(modelFirstName.getValue())) {
            throw new FormException(context, R.string.form_student_message_error_firstname);
        }

        return modelFirstName.getValue().trim();
    }

    public String getLastName() throws FormException {
        if (StringUtils.isBlank(modelLastName.getValue())) {
            throw new FormException(context, R.string.form_student_message_error_lastname);
        }

        return modelLastName.getValue().trim();
    }

    public String getEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_student_message_error_email);
        }

        return modelEmail.getValue().trim();
    }
}
