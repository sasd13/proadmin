package proadmin.tool;

import android.util.Patterns;

import proadmin.content.Teacher;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormValidator {

    private FormValidator() {}

    public static boolean validTeacher(Teacher teacher) {
        FormValidatorCode codeTeacherId = validId(teacher.getId());
        FormValidatorCode codeFirstName = validName(teacher.getFirstName());
        FormValidatorCode codeLastName = validName(teacher.getLastName());
        FormValidatorCode codeEmail = validEmail(teacher.getEmail());
        FormValidatorCode codePassword = validPassword(teacher.getPassword());

        if(codeTeacherId == FormValidatorCode.ERROR_ID
                || codeFirstName == FormValidatorCode.ERROR_NAME
                || codeLastName == FormValidatorCode.ERROR_NAME
                || codeEmail == FormValidatorCode.ERROR_EMAIL
                || codePassword == FormValidatorCode.ERROR_PASSWORD) {
            return false;
        }

        return true;
    }

    public static FormValidatorCode validId(String id) {
        if(id.length() == 0) {
            return FormValidatorCode.ERROR_NAME;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validName(String name) {
        if(name.length() == 0) {
            return FormValidatorCode.ERROR_NAME;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validEmail(String email) {
        if(email.length() == 0 || Patterns.EMAIL_ADDRESS.matcher(email).matches() == false) {
            return FormValidatorCode.ERROR_EMAIL;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validPassword(String password) {
        if(password.length() == 0) {
            return FormValidatorCode.ERROR_PASSWORD;
        }

        return FormValidatorCode.OK;
    }
}
