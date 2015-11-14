package proadmin.beans;

public class Teacher extends AcademicMember {

    private String id, password;

    public Teacher() { super(); }
	
	public Teacher(String firstName, String lastName, String email, String id, String password) {
        super(firstName, lastName, email);

        this.id = id;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
