package proadmin.beans;

public class LeadEvaluation {

    private long id;
    private double planningMark, communicationMark;
    private String planningComment, communicationComment;
    private Student student;
    private Report report;

    public LeadEvaluation() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Report getReport() {
        return this.report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
