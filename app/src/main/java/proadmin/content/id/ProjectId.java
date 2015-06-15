package proadmin.content.id;

import proadmin.content.Grade;
import proadmin.content.Year;
import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class ProjectId extends Id {

    private static final String CLASS_NAME = ProjectId.class.getName();

    private static int count = IdPreferences.getCountId(CLASS_NAME);

    public ProjectId(Grade grade) {
        count++;
        IdPreferences.setCountId(CLASS_NAME, count);

        value = new Year().toString() + getKey() + grade.toString() + count;
    }

    public ProjectId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "P";
    }
}
