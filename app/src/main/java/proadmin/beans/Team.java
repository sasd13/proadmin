package proadmin.beans;

import java.util.List;

public class Team {

    private String code;
    private List<Student> listStudents;
    private List<Report> listReports;

    public Team() {}

    public Team(String code, List<Student> listStudents, List<Report> listReports) {
        this.code = code;
        this.listStudents = listStudents;
        this.listReports = listReports;
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
