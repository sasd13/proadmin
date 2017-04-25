package com.sasd13.proadmin.android.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReportUpdateAdapterI2B implements IAdapter<ReportBean, ReportUpdate> {

    private static final Logger LOGGER = Logger.getLogger(ProjectUpdateAdapterI2B.class);

    @Override
    public ReportUpdate adapt(ReportBean s) {
        ReportUpdate t = new ReportUpdate();

        t.setNumber(s.getId().getId());

        Report report = new Report();

        try {
            report.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(s.getCoreInfo().getDateMeeting()));
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new RuntimeException("ReportAdapterI2B : error parsing date " + s.getCoreInfo().getDateMeeting());
        }

        report.setNumber(s.getCoreInfo().getNumber());
        report.setSession(s.getCoreInfo().getSession());
        report.setComment(s.getCoreInfo().getComment());
        t.setWrapped(report);

        RunningTeam runningTeam = new RunningTeam();
        report.setRunningTeam(runningTeam);

        Running running = new Running();
        running.setYear(s.getLinkedRunningTeam().getLinkedRunning().getYearStarted());
        runningTeam.setRunning(running);

        Project project = new Project();
        project.setCode(s.getLinkedRunningTeam().getLinkedRunning().getLinkedProject().getCode());
        running.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setIntermediary(s.getLinkedRunningTeam().getLinkedRunning().getLinkedTeacher().getIntermediary());
        running.setTeacher(teacher);

        Team team = new Team();
        team.setNumber(s.getLinkedRunningTeam().getLinkedTeam().getNumber());
        runningTeam.setTeam(team);

        AcademicLevel academicLevel = new AcademicLevel();
        academicLevel.setCode(s.getLinkedRunningTeam().getLinkedAcademicLevel().getCode());
        runningTeam.setAcademicLevel(academicLevel);

        return t;
    }
}
