/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ReportDAO extends AbstractDAO implements IReportDAO, IConditionnal {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	public ReportDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ReportDTO create(Report report) {
		ReportDTO dto = new ReportDTO(report);
		RunningTeam runningTeam = report.getRunningTeam();

		dto.setRunningTeam(readRunningTeam(runningTeam.getRunning().getYear(), runningTeam.getRunning().getProject().getCode(), runningTeam.getRunning().getTeacher().getNumber(), runningTeam.getTeam().getNumber(), runningTeam.getAcademicLevel().getCode()));
		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	private RunningTeamDTO readRunningTeam(int year, String projectCode, String teacherNumber, String teamNumber, String academicLevelCode) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(year) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { projectCode });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { teacherNumber });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { teamNumber });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { academicLevelCode });

		return runningTeamDAO.read(parameters).get(0);
	}

	@Override
	public void update(List<ReportUpdateWrapper> updateWrappers) {
		ReportUpdateWrapper updateWrapper;
		ReportDTO dto;
		RunningTeam runningTeam;

		for (int i = 0; i < updateWrappers.size(); i++) {
			updateWrapper = updateWrappers.get(i);
			dto = read(updateWrapper.getNumber());
			runningTeam = updateWrapper.getWrapped().getRunningTeam();

			dto.setDateMeeting(updateWrapper.getWrapped().getDateMeeting());
			dto.setSession(updateWrapper.getWrapped().getSession());
			dto.setComment(updateWrapper.getWrapped().getComment());
			dto.setRunningTeam(readRunningTeam(runningTeam.getRunning().getYear(), runningTeam.getRunning().getProject().getCode(), runningTeam.getRunning().getTeacher().getNumber(), runningTeam.getTeam().getNumber(), runningTeam.getAcademicLevel().getCode()));
			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private ReportDTO read(String number) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<Report> reports) {
		Report report;
		ReportDTO dto;

		for (int i = 0; i < reports.size(); i++) {
			report = reports.get(i);
			dto = read(report.getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from reports r");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<ReportDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return "r.number" + " = ?";
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return "r.session" + " = ?";
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return "r.runningTeam.running.year" + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return "r.runningTeam.running.project.code" + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return "r.runningTeam.running.teacher.number" + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return "r.runningTeam.team.number" + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return "r.runningTeam.academicLevel.code" + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			try {
				query.setParameter(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				query.setParameter(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
