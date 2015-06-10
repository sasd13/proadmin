package proadmin.content;

public class Project {

    private Id id;
    private String title, description;
    private Grade grade;

    public Project() {}
	
	public Project(String title, Grade grade, String description) {
        this.id = new Id();
        this.title = title;
        this.grade = grade;
        this.description = description;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
