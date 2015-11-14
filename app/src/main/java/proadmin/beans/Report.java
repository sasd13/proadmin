package proadmin.beans;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private long id, runninYear, weekNumber;
    private String teamComment;
    private Teacher teacher;
    private Project project;
    private Team team;
    private LeadEvaluation leadEvaluation;
    private List<IndividualEvaluation> listIndividualEvaluations;

    public Report() {}

    public Report(long id, long runninYear, long weekNumber, Teacher teacher, Project project, Team team) {
        this.id = id;
        this.runninYear = runninYear;
        this.weekNumber = weekNumber;
        this.teacher = teacher;
        this.project = project;
        this.team = team;
        this.leadEvaluation = new LeadEvaluation();
        this.listIndividualEvaluations = new ArrayList<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRunninYear() {
        return this.runninYear;
    }

    public void setRunninYear(long runninYear) {
        this.runninYear = runninYear;
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

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
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
        this.listIndividualEvaluations.add(individualEvaluation);
    }

    public void removeIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        this.listIndividualEvaluations.remove(individualEvaluation);
    }

    public IndividualEvaluation getIndividualEvaluation(Student student) {
        for (IndividualEvaluation individualEvaluation : this.listIndividualEvaluations) {
            if (individualEvaluation.getStudent().getId() == student.getId()) {
                return individualEvaluation;
            }
        }

        return null;
    }

    public IndividualEvaluation[] getIndividualEvaluations() {
        return this.listIndividualEvaluations.toArray(new IndividualEvaluation[0]);
    }
}
