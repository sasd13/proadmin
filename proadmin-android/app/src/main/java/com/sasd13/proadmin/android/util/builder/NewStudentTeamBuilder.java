package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewStudentTeamBuilder implements IBuilder<StudentTeam> {

    private Student student;
    private Team team;

    public NewStudentTeamBuilder(Student student, Team team) {
        this.student = student;
        this.team = team;
    }

    @Override
    public StudentTeam build() {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setStudent(student);
        studentTeam.setTeam(team);

        return studentTeam;
    }
}
