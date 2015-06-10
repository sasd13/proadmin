package proadmin.content;

public class Squad {

    private Id id;
    private Year year;
    private Project project;
    private Teacher teacher;
    private ListStudents listStudents;
    private ListReports listReports;

    public Squad() {}

    public Squad(Year year, Project project, Teacher teacher) {
        this.id = new Id();
        this.year = year;
        this.project = project;
        this.teacher = teacher;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Year getYear() {
        return this.year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ListStudents getListStudents() {
        return this.listStudents;
    }

    public void setListStudents(ListStudents listStudents) {
        this.listStudents = listStudents;
    }

    public ListReports getListReports() {
        return this.listReports;
    }

    public void setListReports(ListReports listReports) {
        this.listReports = listReports;
    }
}
