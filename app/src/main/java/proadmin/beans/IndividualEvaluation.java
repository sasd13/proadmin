package proadmin.beans;

public class IndividualEvaluation {

    private double mark;
    private Student student;

    public IndividualEvaluation() {}

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
}
