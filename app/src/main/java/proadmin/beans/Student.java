package proadmin.beans;

public class Student extends AcademicMember {

    private String id;
    private AcademicLevel academicLevel;

    public Student() { super(); }
	
	public Student(String firstName, String lastName, String email, String id, AcademicLevel academicLevel) {
        super(firstName, lastName, email);

        this.id = id;
        this.academicLevel = academicLevel;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
