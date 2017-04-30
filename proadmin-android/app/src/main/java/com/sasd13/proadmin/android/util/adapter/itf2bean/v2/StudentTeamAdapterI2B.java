package com.sasd13.proadmin.android.util.adapter.itf2bean.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterI2B implements IAdapter<StudentTeamBean, StudentTeam> {

    @Override
    public StudentTeam adapt(StudentTeamBean s) {
        StudentTeam t = new StudentTeam();

        t.setId(Long.valueOf(s.getId().getId()));

        Student running = new Student();
        running.setId(Long.valueOf(s.getLinkedStudent().getId()));
        t.setStudent(running);

        Team team = new Team();
        team.setId(Long.valueOf(s.getLinkedTeam().getId()));
        t.setTeam(team);

        return t;
    }
}
