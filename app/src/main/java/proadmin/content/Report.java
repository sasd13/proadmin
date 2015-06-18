package proadmin.content;

import proadmin.content.id.ReportId;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;

public class Report {

    private ReportId id;
    private SquadId squadId;
    private long numberWeek;
    private StudentId projectLeadId;
    private String planningComment, communicationComment, comment;
    private Note planningNote, communicationNote;
    private MapNotes mapNotes;

    public Report() {}

    public Report(SquadId squadId, long numberWeek, StudentId projectLeadId) {
        this.id = new ReportId(squadId, numberWeek);
        this.squadId = squadId;
        this.numberWeek = numberWeek;
        this.projectLeadId = projectLeadId;
    }

    public ReportId getId() {
        return this.id;
    }

    public void setId(ReportId id) {
        this.id = id;
    }

    public SquadId getSquadId() {
        return this.squadId;
    }

    public void setSquadId(SquadId squadId) {
        this.squadId = squadId;
    }

    public long getNumberWeek() {
        return this.numberWeek;
    }

    public void setNumberWeek(long numberWeek) {
        this.numberWeek = numberWeek;
    }

    public StudentId getProjectLeadId() {
        return this.projectLeadId;
    }

    public void setProjectLeadId(StudentId projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

    public Note getPlanningNote() {
        return this.planningNote;
    }

    public void setPlanningNote(Note planningNote) {
        this.planningNote = planningNote;
    }

    public String getPlanningComment() {
        return this.planningComment;
    }

    public void setPlanningComment(String planningComment) {
        this.planningComment = planningComment;
    }

    public Note getCommunicationNote() {
        return this.communicationNote;
    }

    public void setCommunicationNote(Note communicationNote) {
        this.communicationNote = communicationNote;
    }

    public String getCommunicationComment() {
        return this.communicationComment;
    }

    public void setCommunicationComment(String communicationComment) {
        this.communicationComment = communicationComment;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MapNotes getMapNotes() {
        return this.mapNotes;
    }

    public void setMapNotes(MapNotes mapNotes) {
        this.mapNotes = mapNotes;
    }
}
