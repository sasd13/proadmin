package proadmin.manager.data;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataException extends Exception {

    private String message;

    public DataException (String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
