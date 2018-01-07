package com.sasd13.proadmin.android.util.adapter.itf.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterI2M implements IAdapter<StudentTeamBean, StudentTeam> {

    @Override
    public StudentTeam adapt(StudentTeamBean s) {
        StudentTeam t = new StudentTeam();

        t.setId(Long.valueOf(s.getId().getId()));

        Student student = new Student();
        student.setId(Long.valueOf(s.getLinkedStudent().getId()));
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        student.setFirstName(s.getLinkedStudent().getFirstName());
        student.setLastName(s.getLinkedStudent().getLastName());
        student.setEmail(s.getLinkedStudent().getEmail());
        t.setStudent(student);

        Team team = new Team();
        team.setId(Long.valueOf(s.getLinkedTeam().getId()));
        team.setNumber(s.getLinkedTeam().getNumber());
        t.setTeam(team);

        return t;
    }
}
