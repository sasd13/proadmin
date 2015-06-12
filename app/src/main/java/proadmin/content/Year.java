package proadmin.content;

import java.util.Calendar;

/**
 * Created by Samir on 10/06/2015.
 */
public class Year {

    private long value;

    public Year() {
        this.value = Calendar.getInstance().get(Calendar.YEAR);
    }

    public Year(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }
}
