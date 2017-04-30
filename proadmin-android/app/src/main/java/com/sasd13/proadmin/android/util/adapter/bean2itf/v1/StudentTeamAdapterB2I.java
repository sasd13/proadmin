package com.sasd13.proadmin.android.util.adapter.bean2itf.v1;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.studentteam.Id;
import com.sasd13.proadmin.itf.bean.studentteam.LinkedId;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterB2I implements IAdapter<StudentTeam, StudentTeamBean> {

    @Override
    public StudentTeamBean adapt(StudentTeam s) {
        StudentTeamBean t = new StudentTeamBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setStudentIntermediary(s.getStudent().getIntermediary());
        linkedId.setTeamNumber(s.getTeam().getNumber());
        id.setLinkedId(linkedId);

        LinkedStudent linkedStudent = new LinkedStudent();
        linkedStudent.setIntermediary(s.getStudent().getIntermediary());
        t.setLinkedStudent(linkedStudent);

        LinkedTeam linkedTeam = new LinkedTeam();
        linkedTeam.setNumber(s.getTeam().getNumber());
        t.setLinkedTeam(linkedTeam);

        return t;
    }
}
