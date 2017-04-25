package com.sasd13.proadmin.android.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReportAdapterI2B implements IAdapter<ReportBean, Report> {

    private static final Logger LOGGER = Logger.getLogger(ReportAdapterI2B.class);

    @Override
    public Report adapt(ReportBean s) {
        Report t = new Report();

        try {
            t.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(s.getCoreInfo().getDateMeeting()));
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new RuntimeException("ReportAdapterI2B : error parsing date " + s.getCoreInfo().getDateMeeting());
        }

        t.setNumber(s.getCoreInfo().getNumber());
        t.setSession(s.getCoreInfo().getSession());
        t.setComment(s.getCoreInfo().getComment());

        RunningTeam runningTeam = new RunningTeam();
        t.setRunningTeam(runningTeam);

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
