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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.db.dao.ITeamDAO;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;
import com.sasd13.proadmin.ws2.db.dto.TeamDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningTeamDAO extends AbstractDAO implements IRunningTeamDAO, IConditionnal {

	@Autowired
	private IRunningDAO runningDAO;

	@Autowired
	private ITeamDAO teamDAO;

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	public RunningTeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public RunningTeamDTO create(RunningTeam runningTeam) {
		RunningTeamDTO dto = new RunningTeamDTO(runningTeam);

		dto.setRunning(readRunning(runningTeam.getRunning().getYear(), runningTeam.getRunning().getProject().getCode(), runningTeam.getRunning().getTeacher().getNumber()));
		dto.setTeam(readTeam(runningTeam.getTeam().getNumber()));
		dto.setAcademicLevel(readAcademicLevel(runningTeam.getAcademicLevel().getCode()));
		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	private RunningDTO readRunning(int year, String projectCode, String teacherNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(year) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { projectCode });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { teacherNumber });

		return runningDAO.read(parameters).get(0);
	}

	private TeamDTO readTeam(String teamNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { teamNumber });

		return teamDAO.read(parameters).get(0);
	}

	private AcademicLevelDTO readAcademicLevel(String academicLevelCode) {
		List<AcademicLevelDTO> dtos = academicLevelDAO.readAll();

		for (AcademicLevelDTO dto : dtos) {
			if (dto.getCode().equalsIgnoreCase(academicLevelCode)) {
				return dto;
			}
		}

		return null;
	}

	@Override
	public void update(List<RunningTeamUpdateWrapper> updateWrappers) {
		RunningTeamUpdateWrapper updateWrapper;
		RunningTeamDTO dto;
		RunningTeam runningTeam;

		for (int i = 0; i < updateWrappers.size(); i++) {
			updateWrapper = updateWrappers.get(i);
			dto = read(updateWrapper.getRunningYear(), updateWrapper.getProjectCode(), updateWrapper.getTeacherNumber(), updateWrapper.getTeamNumber(), updateWrapper.getAcademicLevelCode());
			runningTeam = updateWrapper.getWrapped();

			dto.setRunning(readRunning(runningTeam.getRunning().getYear(), runningTeam.getRunning().getProject().getCode(), runningTeam.getRunning().getTeacher().getNumber()));
			dto.setTeam(readTeam(runningTeam.getTeam().getNumber()));
			dto.setAcademicLevel(readAcademicLevel(runningTeam.getAcademicLevel().getCode()));
			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private RunningTeamDTO read(int runningYear, String projectCode, String teacherNumber, String teamNumber, String academicLevelCode) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningYear) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { projectCode });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { teacherNumber });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { teamNumber });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { academicLevelCode });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<RunningTeam> runningTeams) {
		RunningTeam runningTeam;
		RunningTeamDTO dto;

		for (int i = 0; i < runningTeams.size(); i++) {
			runningTeam = runningTeams.get(i);
			dto = read(runningTeam.getRunning().getYear(), runningTeam.getRunning().getProject().getCode(), runningTeam.getRunning().getTeacher().getNumber(), runningTeam.getTeam().getNumber(), runningTeam.getAcademicLevel().getCode());

			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RunningTeamDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from runningteams rt");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<RunningTeamDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return "rt.running.year" + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return "rt.running.project.code" + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return "rt.running.teacher.number" + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return "rt.team.number" + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return "rt.academicLevel.code" + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
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
