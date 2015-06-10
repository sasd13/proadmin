package proadmin.content;

public class Student extends Person {

    private Id id;
    private String email;

    public Student() {}
	
	public Student(String firstName, String lastName, String email) {
        super(firstName, lastName);

        this.id = new Id();
        this.email = email;
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
}
