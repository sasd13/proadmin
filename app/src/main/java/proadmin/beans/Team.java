package proadmin.beans;

import java.util.List;

public class Team {

    private long id, runningYear;
    private String code;
    private List<Student> listStudents;
    private List<Report> listReports;

    public Team() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRunningYear() {
        return this.runningYear;
    }

    public void setRunningYear(long runningYear) {
        this.runningYear = runningYear;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Student> getListStudents() {
        return this.listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    public List<Report> getListReports() {
        return this.listReports;
    }

    public void setListReports(List<Report> listReports) {
        this.listReports = listReports;
    }
}
