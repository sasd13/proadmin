package proadmin.content.id;

import proadmin.content.Grade;
import proadmin.content.Year;

/**
 * Created by Samir on 14/06/2015.
 */
public class ProjectId extends Id {

    private static int count = 0;

    public ProjectId(Grade grade) {
        count++;

        setValue(new Year().toString() + getKey() + grade.toString() + count);
    }

    public ProjectId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "P";
    }
}
