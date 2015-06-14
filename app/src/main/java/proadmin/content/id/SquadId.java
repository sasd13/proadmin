package proadmin.content.id;

import proadmin.util.IdPreferences;

/**
 * Created by Samir on 14/06/2015.
 */
public class SquadId extends Id {

    private static int count = IdPreferences.getCountId(SquadId.class.getName());

    public SquadId(ProjectId projectId) {
        count++;
        IdPreferences.setCountId(ProjectId.class.getName(), count);

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
