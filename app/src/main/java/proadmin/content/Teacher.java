package proadmin.content;

public class Teacher extends Person {

    private Id id;
    private String email, password;

    public Teacher() {}
	
	public Teacher(String firstName, String lastName, String email, String password) {
        super(firstName, lastName);

        this.id = new Id();
        this.email = email;
        this.password = password;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
