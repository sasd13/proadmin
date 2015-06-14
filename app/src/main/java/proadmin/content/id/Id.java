package proadmin.content.id;

/**
 * Created by Samir on 10/06/2015.
 */
public abstract class Id {

    protected String value;

    protected Id() {}

    public Id(String value) {
        this.value = value;
    }

    public abstract String getKey();

    @Override
    public String toString() {
        return this.value;
    }
}
