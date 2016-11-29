package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class StudentsFromStudentTeamBuilder implements IBuilder<List<Student>> {

    private List<StudentTeam> studentTeams;

    public StudentsFromStudentTeamBuilder(List<StudentTeam> studentTeams) {
        this.studentTeams = studentTeams;
    }

    @Override
    public List<Student> build() {
        List<Student> students = new ArrayList<>();

        for (StudentTeam studentTeam : studentTeams) {
            students.add(studentTeam.getStudent());
        }

        return students;
    }
}
