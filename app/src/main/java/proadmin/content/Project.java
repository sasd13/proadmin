package proadmin.content;

public class Project {

    private String id;
    private Year yearCreation;
    private String title, description;
    private Grade grade;

    public Project() {}
	
	public Project(String id, Year yearCreation, String title, Grade grade, String description) {
        this.id = id;
        this.yearCreation = yearCreation;
        this.title = title;
        this.grade = grade;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Year getYearCreation() {
        return this.yearCreation;
    }

    public void setYearCreation(Year yearCreation) {
        this.yearCreation = yearCreation;
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
