package proadmin.content.id;

import proadmin.content.Year;
import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class StudentId extends Id {

    private static final String CLASS_NAME = StudentId.class.getName();

    private static int count = IdPreferences.getCountId(CLASS_NAME);

    public StudentId() {
        count++;
        IdPreferences.setCountId(CLASS_NAME, count);

        value = new Year().toString() + getKey() + count;
    }

    public StudentId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "ST";
    }
}
