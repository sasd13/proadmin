package proadmin.content.id;

/**
 * Created by Samir on 14/06/2015.
 */
public class ReportId extends Id {

    public ReportId(Id squadId, long numberWeek) {
        value = squadId.toString() + getKey() + numberWeek;
    }

    public ReportId(String value) {
        super(value);
    }

    @Override
    public String getKey() {
        return "R";
    }
}
