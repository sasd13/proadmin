package proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private long id, weekNumber;
    private Timestamp dateMeeting;
    private String teamComment;
    private Team team;
    private LeadEvaluation leadEvaluation;
    private List<IndividualEvaluation> individualEvaluations;

    public Report() {
        this.leadEvaluation = new LeadEvaluation();
        this.individualEvaluations = new ArrayList<>();

        this.leadEvaluation.setReport(this);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateMeeting() {
        return this.dateMeeting;
    }

    public void setDateMeeting(Timestamp dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public long getWeekNumber() {
        return this.weekNumber;
    }

    public void setWeekNumber(long weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getTeamComment() {
        return this.teamComment;
    }

    public void setTeamComment(String teamComment) {
        this.teamComment = teamComment;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LeadEvaluation getLeadEvaluation() {
        return this.leadEvaluation;
    }

    public void addIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        this.individualEvaluations.add(individualEvaluation);

        individualEvaluation.setReport(this);
    }

    public void removeIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        this.individualEvaluations.remove(individualEvaluation);
    }

    public IndividualEvaluation[] getIndividualEvaluations() {
        return this.individualEvaluations.toArray(new IndividualEvaluation[this.individualEvaluations.size()]);
    }
}
