package proadmin.content;

/**
 * Created by Samir on 10/06/2015.
 */
public class Note {

    private long value;

    public Note() {}

    public Note(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
