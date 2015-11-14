package proadmin.beans;

public class Project {

    private AcademicLevel academicLevel;
    private String title, description;

    public Project() {}
	
	public Project(AcademicLevel academicLevel, String title, String description) {
        this.academicLevel = academicLevel;
        this.title = title;
        this.description = description;
    }

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
