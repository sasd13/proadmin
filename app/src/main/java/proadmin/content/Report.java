package proadmin.content;

public class Report {

    private Id id, projectLeadId;
    private long numberWeek;
    private String planningComment, communicationComment, comment;
    private Note planningNote, communicationNote;
    private MapNotes mapNotes;

    public Report() {}

    public Report(long numberWeek, Id projectLeadId) {
        this.id = new Id();
        this.numberWeek = numberWeek;
        this.projectLeadId = projectLeadId;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public long getNumberWeek() {
        return this.numberWeek;
    }

    public void setNumberWeek(long numberWeek) {
        this.numberWeek = numberWeek;
    }

    public Id getProjectLeadId() {
        return this.projectLeadId;
    }

    public void setProjectLeadId(Id projectLeadId) {
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
