package proadmin.content;

import java.util.LinkedHashMap;

/**
 * Created by Samir on 09/06/2015.
 */
public class MapNotes {

    private LinkedHashMap<String, Note> map;

    public MapNotes() {
        this.map = new LinkedHashMap<>();
    }

    public void put(String studentId, Note note) {
        this.map.put(studentId, note);
    }

    public void remove(String studentId) {
        this.map.remove(studentId);
    }

    public Note get(String studentId) {
        return this.map.get(studentId);
    }

    public Note get(int index) {
        return this.map.get(index);
    }

    public boolean contains(String studentId) {
        return this.map.containsKey(studentId);
    }

    public int size() {
        return this.map.size();
    }

    public void clear() {
        this.map.clear();
    }

    public String[] getKeys() {
        return (String[]) this.map.keySet().toArray();
    }
}
