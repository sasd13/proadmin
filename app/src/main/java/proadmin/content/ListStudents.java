package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.Id;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListStudents implements Iterable, Viewable {

    private List<Student> listStudents;

    public ListStudents() {
        this.listStudents = new ArrayList<>();
    }

    public boolean add(Student student) {
        return this.listStudents.add(student);
    }

    public boolean remove(Student student) {
        return this.listStudents.remove(student);
    }

    public Student get(int index) {
        return this.listStudents.get(index);
    }

    public Student get(StudentId studentId) {
        for (Student student : this.listStudents) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    public int size() {
        return this.listStudents.size();
    }

    @Override
    public Iterator iterator() {
        return this.listStudents.iterator();
    }

    @Override
    public List<String> getListSrings() {
        List<String> list = new ArrayList<>();

        for (Student student : this.listStudents) {
            list.add(student.getFirstName() + " " + student.getLastName());
        }

        return list;
    }
}
