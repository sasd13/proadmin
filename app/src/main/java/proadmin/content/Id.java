package proadmin.content;

/**
 * Created by Samir on 10/06/2015.
 */
public class Id {

    private static int count = 0;

    private String id;

    public Id() {
        count++;
    }

    public Id(String id) {
        this();

        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
