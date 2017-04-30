package com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedRunningTeam;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.report.CoreInfo;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

import java.text.SimpleDateFormat;

public class ReportUpdateAdapterB2I implements IAdapter<ReportUpdate, ReportBean> {


    @Override
    public ReportBean adapt(ReportUpdate s) {
        ReportBean t = new ReportBean();

        Id id = new Id();
        id.setId(s.getNumber());
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setNumber(s.getWrapped().getNumber());
        coreInfo.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(s.getWrapped().getDateMeeting()));
        coreInfo.setSession(s.getWrapped().getSession());
        coreInfo.setComment(s.getWrapped().getComment());
        t.setCoreInfo(coreInfo);

        LinkedRunningTeam linkedRunningTeam = new LinkedRunningTeam();
        t.setLinkedRunningTeam(linkedRunningTeam);

        LinkedRunning linkedRunning = new LinkedRunning();
        linkedRunning.setYearStarted(s.getWrapped().getRunningTeam().getRunning().getYear());
        linkedRunningTeam.setLinkedRunning(linkedRunning);

        LinkedProject linkedProject = new LinkedProject();
        linkedProject.setCode(s.getWrapped().getRunningTeam().getRunning().getProject().getCode());
        linkedRunning.setLinkedProject(linkedProject);

        LinkedTeacher linkedTeacher = new LinkedTeacher();
        linkedTeacher.setIntermediary(s.getWrapped().getRunningTeam().getRunning().getTeacher().getIntermediary());
        linkedRunning.setLinkedTeacher(linkedTeacher);

        LinkedTeam linkedTeam = new LinkedTeam();
        linkedTeam.setNumber(s.getWrapped().getRunningTeam().getTeam().getNumber());
        linkedRunningTeam.setLinkedTeam(linkedTeam);

        LinkedAcademicLevel linkedAcademicLevel = new LinkedAcademicLevel();
        linkedAcademicLevel.setCode(s.getWrapped().getRunningTeam().getAcademicLevel().getCode());
        linkedRunningTeam.setLinkedAcademicLevel(linkedAcademicLevel);

        return t;
    }
}
