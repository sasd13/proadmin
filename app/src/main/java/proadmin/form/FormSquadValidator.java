package proadmin.form;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormSquadValidator {

    private FormSquadValidator() {}

    public static void validForm(String name) throws FormException {
        validName(name);
    }

    private static void validName(String name) throws FormException {
        if (name.length() == 0) {
            throw new FormException("name is empty");
        }
    }
}
