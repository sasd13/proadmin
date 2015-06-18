package proadmin.content;

import java.util.LinkedHashMap;

import proadmin.content.id.StudentId;

/**
 * Created by Samir on 09/06/2015.
 */
public class MapNotes {

    private LinkedHashMap<StudentId, Note> map;

    public MapNotes() {
        this.map = new LinkedHashMap<>();
    }

    public void put(StudentId studentId, Note note) {
        this.map.put(studentId, note);
    }

    public void remove(StudentId studentId) {
        this.map.remove(studentId);
    }

    public Note get(StudentId studentId) {
        return this.map.get(studentId);
    }

    public Note get(int index) {
        return this.map.get(index);
    }

    public boolean contains(StudentId studentId) {
        return this.map.containsKey(studentId);
    }

    public int size() {
        return this.map.size();
    }

    public void clear() {
        this.map.clear();
    }

    public StudentId[] getKeys() {
        return (StudentId[]) this.map.keySet().toArray();
    }
}
