package proadmin.content.id;

import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class SquadId extends Id {

    private static final String CLASS_NAME = SquadId.class.getName();

    private static int count = IdPreferences.getCountId(CLASS_NAME);

    public SquadId(ProjectId projectId) {
        count++;
        IdPreferences.setCountId(CLASS_NAME, count);

        value = projectId.toString() + getKey() + count;
    }

    public SquadId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "SQ";
    }
}
