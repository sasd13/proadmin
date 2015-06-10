package proadmin.content;

public class Student extends Person {

    private static int count = 0;

    private String id, email;

    public Student() {
        count++;
    }
	
	public Student(String firstName, String lastName, String email) {
        super(firstName, lastName);

        count++;

        this.id = "id-student-" + count;
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
