package proadmin.content.id;

import proadmin.content.Year;

/**
 * Created by Samir on 14/06/2015.
 */
public class StudentId extends Id {

    private static int count = 0;

    public StudentId() {
        count++;

        setValue(new Year().toString() + getKey() + count);
    }

    public StudentId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "ST";
    }
}
