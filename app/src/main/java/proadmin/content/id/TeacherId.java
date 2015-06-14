package proadmin.content.id;

import proadmin.content.Year;
import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class TeacherId extends Id {

    private static int count = IdPreferences.getCountId(TeacherId.class.getName());

    public TeacherId() {
        count++;
        IdPreferences.setCountId(ProjectId.class.getName(), count);

        value = new Year().toString() + getKey() + count;
    }

    public TeacherId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "T";
    }
}
