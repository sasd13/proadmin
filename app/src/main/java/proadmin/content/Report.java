package proadmin.content;

public class Report {

    private String id, squadId, projectLeadId;
    private long numberWeek;
    private String planningComment, communicationComment, comment;
    private Note planningNote, communicationNote;
    private MapNotes mapNotes;

    public Report() {}

    public Report(String id, String squadId, long numberWeek, String projectLeadId) {
        this.id = id;
        this.squadId = squadId;
        this.numberWeek = numberWeek;
        this.projectLeadId = projectLeadId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSquadId() {
        return this.squadId;
    }

    public void setSquadId(String squadId) {
        this.squadId = squadId;
    }

    public long getNumberWeek() {
        return this.numberWeek;
    }

    public void setNumberWeek(long numberWeek) {
        this.numberWeek = numberWeek;
    }

    public String getProjectLeadId() {
        return this.projectLeadId;
    }

    public void setProjectLeadId(String projectLeadId) {
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
