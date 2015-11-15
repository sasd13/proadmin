package proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import proadmin.bean.member.Student;
import proadmin.bean.member.Teacher;
import proadmin.bean.project.Project;

public class Report {

    private long id, weekNumber;
    private Timestamp dateMeeting;
    private String teamComment;
    private Teacher teacher;
    private Project project;
    private Team team;
    private LeadEvaluation leadEvaluation;
    private List<IndividualEvaluation> listIndividualEvaluations;

    public Report() {
        this.leadEvaluation = new LeadEvaluation();
        this.listIndividualEvaluations = new ArrayList<>();

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
        individualEvaluation.setReport(this);

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
        return this.listIndividualEvaluations.toArray(new IndividualEvaluation[this.listIndividualEvaluations.size()]);
    }
}
