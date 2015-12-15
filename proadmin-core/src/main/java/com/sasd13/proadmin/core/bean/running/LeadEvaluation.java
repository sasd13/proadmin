package com.sasd13.proadmin.core.bean.running;

public class LeadEvaluation extends Evaluation {

    private float planningMark, communicationMark;
    private String planningComment, communicationComment;

    public float getPlanningMark() {
        return this.planningMark;
    }

    public void setPlanningMark(float planningMark) {
        this.planningMark = planningMark;
    }

    public float getCommunicationMark() {
        return this.communicationMark;
    }

    public void setCommunicationMark(float communicationMark) {
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
