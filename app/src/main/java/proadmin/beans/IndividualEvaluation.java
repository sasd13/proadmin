package proadmin.beans;

public class IndividualEvaluation {

    private long id;
    private double mark;
    private Student student;
    private Report report;

    public IndividualEvaluation() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMark() {
        return this.mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
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
