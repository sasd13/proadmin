package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.MapNotes;

/**
 * Created by Samir on 11/06/2015.
 */
public interface NoteAccessor {

    void insertNotes(MapNotes mapNotes, Id reportId);

    void updateNotes(MapNotes mapNotes, Id reportId);

    void deleteNotes(Id studentId, Id reportId);

    MapNotes selectNotes(Id reportId);
}
