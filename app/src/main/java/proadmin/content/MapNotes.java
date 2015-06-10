package proadmin.content;

import java.util.LinkedHashMap;

/**
 * Created by Samir on 05/06/2015.
 */
public class MapNotes {

    private LinkedHashMap<Id, Note> mapNotes;

    public MapNotes() {
        this.mapNotes = new LinkedHashMap<>();
    }

    public void put(Id studentId, Note note) {
        this.mapNotes.put(studentId, note);
    }

    public void remove(Id studentId) {
        this.mapNotes.remove(studentId);
    }

    public Note get(Id studentId) {
        return this.mapNotes.get(studentId);
    }

    public Note get(int index) {
        return this.mapNotes.get(index);
    }

    public Id[] getKeys() {
        return (Id[]) this.mapNotes.keySet().toArray();
    }

    public int size() {
        return this.mapNotes.size();
    }
}
