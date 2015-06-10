package proadmin.content;

public class Report {

    private static int count = 0;

    private String id, projectLeadId, planningComment, communicationComment, comment;
    private long numberWeek, planningNote, communicationNote;
    private MapNotes mapNotes;

    public Report() {
        count++;
    }

    public Report(long numberWeek, String projectLeadId) {
        count++;

        this.id = "id-report-" + count;
        this.numberWeek = numberWeek;
        this.projectLeadId = projectLeadId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getPlanningNote() {
        return this.planningNote;
    }

    public void setPlanningNote(long planningNote) {
        this.planningNote = planningNote;
    }

    public String getPlanningComment() {
        return this.planningComment;
    }

    public void setPlanningComment(String planningComment) {
        this.planningComment = planningComment;
    }

    public long getCommunicationNote() {
        return this.communicationNote;
    }

    public void setCommunicationNote(long communicationNote) {
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
