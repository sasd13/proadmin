package com.sasd13.proadmin.android.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterI2B implements IAdapter<StudentTeamBean, StudentTeam> {

    @Override
    public StudentTeam adapt(StudentTeamBean s) {
        StudentTeam t = new StudentTeam();

        Student student = new Student();
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        t.setStudent(student);

        Team team = new Team();
        team.setNumber(s.getLinkedTeam().getNumber());
        t.setTeam(team);

        return t;
    }
}
