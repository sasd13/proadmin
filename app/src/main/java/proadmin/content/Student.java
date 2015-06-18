package proadmin.content;

import proadmin.content.id.StudentId;

public class Student extends Person {

    private StudentId id;
    private String email;

    public Student() {}
	
	public Student(String firstName, String lastName, String email) {
        super(firstName, lastName);

        this.id = new StudentId();
        this.email = email;
    }

    public StudentId getId() {
        return this.id;
    }

    public void setId(StudentId id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
