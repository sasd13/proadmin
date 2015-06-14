package proadmin.content;

import java.util.LinkedHashMap;

import proadmin.content.id.StudentId;

/**
 * Created by Samir on 05/06/2015.
 */
public class MapNotes {

    private LinkedHashMap<StudentId, Note> mapNotes;

    public MapNotes() {
        this.mapNotes = new LinkedHashMap<>();
    }

    public void put(StudentId studentId, Note note) {
        this.mapNotes.put(studentId, note);
    }

    public void remove(StudentId studentId) {
        this.mapNotes.remove(studentId);
    }

    public Note get(StudentId studentId) {
        return this.mapNotes.get(studentId);
    }

    public Note get(int index) {
        return this.mapNotes.get(index);
    }

    public StudentId[] getKeys() {
        return (StudentId[]) this.mapNotes.keySet().toArray();
    }

    public int size() {
        return this.mapNotes.size();
    }
}
