package proadmin.content;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Samir on 05/06/2015.
 */
public class MapNotes {

    private LinkedHashMap<String, Long> mapNotes;

    public MapNotes() {
        this.mapNotes = new LinkedHashMap<>();
    }

    public void put(String studentId, long note) {
        this.mapNotes.put(studentId, new Long(note));
    }

    public void remove(String studentId) {
        this.mapNotes.remove(studentId);
    }

    public long get(String studentId) {
        return this.mapNotes.get(studentId);
    }

    public long get(int index) {
        return this.mapNotes.get(index);
    }

    public String[] getKeys() {
        return (String[]) this.mapNotes.keySet().toArray();
    }

    public int size() {
        return this.mapNotes.size();
    }
}
