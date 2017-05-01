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
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.IReportDAO;
import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ReportDAO extends AbstractDAO implements IReportDAO {

	public ReportDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Report create(Report report) {
		currentSession().save(report);
		currentSession().flush();

		return report;
	}

	@Override
	public void update(Report report) {
		currentSession().update(report);
		currentSession().flush();
	}

	@Override
	public void delete(Report report) {
		currentSession().remove(report);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Report rp");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Report>) query.getResultList();
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return "rp.number = ?";
		} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(key)) {
			return "rp.session = ?";
		} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.year = ?";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.project.code = ?";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.teacher.intermediary = ?";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.team.number = ?";
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.academicLevel.code = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return "rp.number";
		} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(key)) {
			return "rp.session";
		} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.year";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.project.code";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.running.teacher.intermediary";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.team.number";
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			return "rp.runningTeam.academicLevel.code";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
		} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
