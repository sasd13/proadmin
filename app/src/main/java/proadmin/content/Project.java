package proadmin.content;

import proadmin.content.id.ProjectId;

public class Project {

    private ProjectId id;
    private String title, description;
    private Grade grade;

    public Project() {}
	
	public Project(ProjectId id, String title, Grade grade, String description) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.description = description;
    }

    public ProjectId getId() {
        return this.id;
    }

    public void setId(ProjectId id) {
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
