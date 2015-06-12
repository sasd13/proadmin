package proadmin;

/**
 * Created by Samir on 11/06/2015.
 */
public class ProAdminException extends Exception {

    private String message;

    public ProAdminException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
