package proadmin.content.id;

import proadmin.content.Grade;
import proadmin.content.Year;
import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class ProjectId extends Id {

    private static int count = IdPreferences.getCountId(ProjectId.class.getName());

    public ProjectId(Grade grade) {
        count++;
        IdPreferences.setCountId(ProjectId.class.getName(), count);

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
