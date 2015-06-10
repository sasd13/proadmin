package proadmin.content;

public class Squad {

    private static int count = 0;

    private String id;
    private long year;
    private Project project;
    private Teacher teacher;
    private ListStudents listStudents;
    private ListReports listReports;

    public Squad() {
        count++;
    }

    public Squad(long year, Project project, Teacher teacher) {
        count++;

        this.id = "id-squad-" + count;
        this.year = year;
        this.project = project;
        this.teacher = teacher;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getYear() {
        return this.year;
    }

    public void setYear(long year) {
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
