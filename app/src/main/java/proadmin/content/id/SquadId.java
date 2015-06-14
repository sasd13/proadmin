package proadmin.content.id;

/**
 * Created by Samir on 14/06/2015.
 */
public class SquadId extends Id {

    private static int count = 0;

    public SquadId(Id projectId) {
        count++;

        setValue(projectId.toString() + getKey() + count);
    }

    public SquadId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "SQ";
    }
}
