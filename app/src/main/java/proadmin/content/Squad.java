package proadmin.content;

import proadmin.content.id.SquadId;

public class Squad {

    private SquadId id;
    private Teacher teacher;
    private Year year;
    private Project project;
    private ListStudents listStudents;
    private ListReports listReports;

    public Squad() {}

    public Squad(SquadId id, Year year, Project project, Teacher teacher) {
        this.id = id;
        this.teacher = teacher;
        this.year = year;
        this.project = project;
    }

    public SquadId getId() {
        return this.id;
    }

    public void setId(SquadId id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
