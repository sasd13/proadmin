package proadmin.beans;

public class LeadEvaluation {

    private double planningMark, communicationMark;
    private String planningComment, communicationComment;
    private Student student;

    public LeadEvaluation() {}

    public LeadEvaluation(Student student, double planningMark, double communicationMark) {
        this.student = student;
        this.planningMark = planningMark;
        this.communicationMark = communicationMark;
    }

    public double getPlanningMark() {
        return this.planningMark;
    }

    public void setPlanningMark(double planningMark) {
        this.planningMark = planningMark;
    }

    public double getCommunicationMark() {
        return this.communicationMark;
    }

    public void setCommunicationMark(double communicationMark) {
        this.communicationMark = communicationMark;
    }

    public String getPlanningComment() {
        return this.planningComment;
    }

    public void setPlanningComment(String planningComment) {
        this.planningComment = planningComment;
    }

    public String getCommunicationComment() {
        return this.communicationComment;
    }

    public void setCommunicationComment(String communicationComment) {
        this.communicationComment = communicationComment;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
