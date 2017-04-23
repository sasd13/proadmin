/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.backend.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.dao.IReportDAO;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;
import com.sasd13.proadmin.backend.util.adapter.bean2dto.ReportAdapterB2D;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ReportDAO extends AbstractDAO implements IReportDAO, IConditionnal {

	public ReportDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ReportDTO create(Report report) {
		ReportDTO dto = new ReportAdapterB2D().adapt(report);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(Report report) {
		currentSession().update(new ReportAdapterB2D().adapt(report));
		currentSession().flush();
	}

	@Override
	public void delete(Report report) {
		currentSession().remove(new ReportAdapterB2D().adapt(report));
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from reports rp");

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
			return "rp.number" + " = ?";
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return "rp.session" + " = ?";
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.year" + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.project.code" + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.teacher.intermediary" + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return "rp.runningTeam.team.number" + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return "rp.runningTeam.academicLevel.code" + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
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