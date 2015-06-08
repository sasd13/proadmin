package proadmin.content;

public class Teacher extends Person {

    private int count = 0;
    private String id, email, password;

    public Teacher() {
        count++;
    }
	
	public Teacher(String firstName, String lastName, String email, String password) {
        super(firstName, lastName);

        count++;

        this.id = "id-teacher-" + count;
        this.email = email;
        this.password = password;
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
