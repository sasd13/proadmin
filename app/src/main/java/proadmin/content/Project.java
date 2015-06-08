package proadmin.content;

public class Project {

    private String id, title, description;
    private Level level;

    public Project() {}
	
	public Project(String id, String title, Level level, String description) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
