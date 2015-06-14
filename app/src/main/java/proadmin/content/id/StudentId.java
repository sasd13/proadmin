package proadmin.content.id;

import proadmin.content.Year;
import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class StudentId extends Id {

    private static int count = IdPreferences.getCountId(StudentId.class.getName());

    public StudentId() {
        count++;
        IdPreferences.setCountId(ProjectId.class.getName(), count);

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
