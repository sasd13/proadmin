package proadmin.beans;

public class Teacher extends AcademicMember {

    private String password;

    public Teacher() { super(); }
	
	public Teacher(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);

        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
