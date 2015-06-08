package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListStudents implements Iterable {

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

    public Student get(String studentId) {
        for (Student student : this.listStudents) {
            if (student.getId().compareTo(studentId) == 0) {
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
}
