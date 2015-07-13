package proadmin.content;

public class Teacher extends Person {

    private String id;
    private String email, password;

    public Teacher() {}
	
	public Teacher(String id, String firstName, String lastName, String email) {
        super(firstName, lastName);

        this.id = id;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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
