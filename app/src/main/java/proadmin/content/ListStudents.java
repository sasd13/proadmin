package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.StudentId;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListStudents implements Iterable, Viewable {

    private List<Student> list;

    public ListStudents() {
        this.list = new ArrayList<>();
    }

    public boolean add(Student student) {
        return this.list.add(student);
    }

    public boolean remove(Student student) {
        return this.list.remove(student);
    }

    public Student get(int index) {
        return this.list.get(index);
    }

    public Student get(StudentId studentId) {
        for (Student student : this.list) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    public boolean contains(StudentId studentId) {
        return this.list.contains(studentId);
    }

    public int size() {
        return this.list.size();
    }

    public void clear() {
        this.list.clear();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }

    @Override
    public List<String> getListSrings() {
        List<String> listStrings = new ArrayList<>();

        for (Student student : this.list) {
            listStrings.add(student.getFirstName() + " " + student.getLastName());
        }

        return listStrings;
    }
}
