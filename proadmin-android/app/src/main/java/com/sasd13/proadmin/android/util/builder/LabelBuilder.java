package com.sasd13.proadmin.android.util.builder;

import com.sasd13.proadmin.android.model.Student;

/**
 * Created by ssaidali2 on 07/05/2017.
 */

public class LabelBuilder {

    public static String studentShortenFullName(Student student) {
        return student.getFirstName() + " " + student.getLastName().substring(0, 1) + ".";
    }

    public static String studentIntermediaryWithFullName(Student student) {
        return student.getIntermediary() + " / " + student.getFullName();
    }

    public static String studentIntermediaryWithShortenFullName(Student student) {
        return student.getIntermediary() + " / " + studentShortenFullName(student);
    }
}
