package com.sasd13.proadmin.bean.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.bean.member.Student;

public class Team {

    private long id;
    private String code;
    private Running running;
    private Student[] students;
    private List<Report> reports;

    public Team() { this.reports = new ArrayList<>(); }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Running getRunning() {
        return this.running;
    }

    public void setRunning(Running running) {
        this.running = running;
    }

    public Student[] getStudents() {
        return this.students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void removeReport(Report report) {
        this.reports.remove(report);
    }

    public Report[] getReports() {
        return this.reports.toArray(new Report[this.reports.size()]);
    }
}
