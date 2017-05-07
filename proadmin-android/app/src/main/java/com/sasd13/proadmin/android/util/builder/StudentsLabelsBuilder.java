package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class StudentsLabelsBuilder implements IBuilder<List<String>> {

    private List<Student> students;

    public StudentsLabelsBuilder(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Student student : students) {
            list.add(student.getIntermediary());
        }

        return list;
    }

    public List<String> buildWithName() {
        List<String> list = new ArrayList<>();

        StringBuilder builder;

        for (Student student : students) {
            builder = new StringBuilder();

            builder.append(LabelBuilder.studentIntermediaryWithShortenFullName(student));

            list.add(builder.toString());
        }

        return list;
    }
}
