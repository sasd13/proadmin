package proadmin.bean.running;

import proadmin.bean.member.Student;

public abstract class Evaluation {

    private long id;
    private Student student;
    private Report report;

    protected Evaluation() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Report getReport() {
        return this.report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
