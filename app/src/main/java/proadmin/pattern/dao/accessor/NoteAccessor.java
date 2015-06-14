package proadmin.pattern.dao.accessor;

import proadmin.content.MapNotes;
import proadmin.content.id.ReportId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface NoteAccessor {

    void insertNotes(MapNotes mapNotes, ReportId reportId);

    void updateNotes(MapNotes mapNotes, ReportId reportId);

    void deleteNotes(StudentId studentId, ReportId reportId);

    MapNotes selectNotes(ReportId reportId);
}
