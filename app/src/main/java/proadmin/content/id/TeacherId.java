package proadmin.content.id;

import proadmin.content.Year;

/**
 * Created by Samir on 14/06/2015.
 */
public class TeacherId extends Id {

    private static int count = 0;

    public TeacherId() {
        count++;

        setValue(new Year().toString() + getKey() + count);
    }

    public TeacherId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "T";
    }
}
