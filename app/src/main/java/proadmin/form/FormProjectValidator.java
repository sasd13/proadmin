package proadmin.form;

import proadmin.content.Year;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormProjectValidator {

    private FormProjectValidator() {}

    public static String validForm(String year,
                                   String title,
                                   String description) {
        String message = null;

        try {
            validYear(year);
            validTitle(title);
            validDescription(description);
        } catch (FormException e) {
            message = e.getMessage();
        }

        return message;
    }

    private static void validYear(String year) throws FormException {
        if (year.compareTo(new Year().toString()) == 0) {
            throw new FormException("date must be current");
        }
    }

    private static void validTitle(String title) throws FormException {
        if (title.length() == 0) {
            throw new FormException("title is empty");
        }
    }

    private static void validDescription(String description) throws FormException {
        if (description.length() == 0) {
            throw new FormException("description is empty");
        }
    }
}
