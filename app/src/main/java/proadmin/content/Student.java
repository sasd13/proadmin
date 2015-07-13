package proadmin.content;

public class Student extends Person {

    private String id;
    private String email;

    public Student() {}
	
	public Student(String id, String firstName, String lastName, String email) {
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
}
