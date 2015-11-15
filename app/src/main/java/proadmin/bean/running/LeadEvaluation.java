package proadmin.bean.running;

public class LeadEvaluation extends Evaluation {

    private double planningMark, communicationMark;
    private String planningComment, communicationComment;

    public LeadEvaluation() { super(); }

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
}
