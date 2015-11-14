package proadmin.beans;

public class Project {

    private String code, title, description;
    private AcademicLevel academicLevel;

    public Project() {}
	
	public Project(String code, AcademicLevel academicLevel, String title, String description) {
        this.code = code;
        this.academicLevel = academicLevel;
        this.title = title;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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
