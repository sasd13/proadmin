package proadmin.form;

import android.util.Patterns;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormUserValidator {

    private FormUserValidator() {}

    public static String validForm(String firstName,
                                   String lastName,
                                   String email) {
        String message = null;

        try {
            validName(firstName);
            validName(lastName);
            validEmail(email);
        } catch (FormException e) {
            message = e.getMessage();
        }

        return message;
    }

    public static String validForm(String firstName,
                                   String lastName,
                                   String email,
                                   String password,
                                   String confirmPassword,
                                   boolean validCheckbox) {
        String message = null;

        try {
            validName(firstName);
            validName(lastName);
            validEmail(email);
            validPassword(password, confirmPassword);
            validTerms(validCheckbox);
        } catch (FormException e) {
            message = e.getMessage();
        }

        return message;
    }

    private static void validName(String name) throws FormException {
        if (name.length() == 0) {
            throw new FormException("name is empty");
        }
    }

    private static void validEmail(String email) throws FormException {
        if (email.length() == 0) {
            throw  new FormException("email is empty");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw new FormException("email is not valid");
        }
    }

    private static void validPassword(String password, String confirmPassword) throws FormException {
        if (password.length() == 0) {
            throw new FormException("password is empty");
        } else if (password.length() < 6) {
            throw new FormException("password is short");
        } else if (password.compareTo(confirmPassword) != 0) {
            throw new FormException("password is not confirmed");
        }
    }

    private static void validTerms(boolean checked) throws FormException {
        if (!checked) {
            throw new FormException("you must valid terms");
        }
    }
}
