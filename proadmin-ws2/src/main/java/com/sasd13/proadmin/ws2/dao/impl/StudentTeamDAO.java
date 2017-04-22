/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.dao.impl;

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
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws2.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws2.dao.ITeamDAO;
import com.sasd13.proadmin.ws2.dao.dto.StudentDTO;
import com.sasd13.proadmin.ws2.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.ws2.dao.dto.TeamDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StudentTeamDAO extends AbstractDAO implements IStudentTeamDAO, IConditionnal {

	@Autowired
	private IStudentDAO studentDAO;

	@Autowired
	private ITeamDAO teamDAO;

	public StudentTeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public StudentTeamDTO create(StudentTeam studentTeam) {
		StudentTeamDTO dto = new StudentTeamDTO(studentTeam);

		dto.setStudent(readStudent(studentTeam.getStudent().getIntermediary()));
		dto.setTeam(readTeam(studentTeam.getTeam().getNumber()));
		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	private StudentDTO readStudent(String studentNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { studentNumber });

		return studentDAO.read(parameters).get(0);
	}

	private TeamDTO readTeam(String teamNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { teamNumber });

		return teamDAO.read(parameters).get(0);
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		StudentTeam studentTeam;
		StudentTeamDTO dto;

		for (int i = 0; i < studentTeams.size(); i++) {
			studentTeam = studentTeams.get(i);
			dto = read(studentTeam.getStudent().getIntermediary(), studentTeam.getTeam().getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private StudentTeamDTO read(String studentNumber, String teamNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.STUDENT.getName(), new String[] { studentNumber });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { teamNumber });

		return read(parameters).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentTeamDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from studentteams st");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<StudentTeamDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "st.student.number" + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return "st.team.number" + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
