package proadmin.data.dao.accessor;

/**
 * Created by Samir on 11/06/2015.
 */
public interface NoteAccessor {

    void insertNotes(MapNotes mapNotes, String reportId);

    void updateNotes(MapNotes mapNotes, String reportId);

    MapNotes selectNotesOfReport(String reportId);
}
