/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.IAcademicLevelUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AcademicLevelDAO extends HibernateSession<AcademicLevel> implements IAcademicLevelDAO {

	public AcademicLevelDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(AcademicLevel academicLevel) {
		AcademicLevelDTO academicLevelDTO = new AcademicLevelDTO(academicLevel);

		HibernateUtils.insert(this, academicLevelDTO);

		return academicLevelDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<AcademicLevel> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(AcademicLevel academicLevel) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), academicLevel);
	}

	@Override
	public AcademicLevel select(long id) {
		return null;
	}

	@Override
	public List<AcademicLevel> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<AcademicLevel> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(AcademicLevel academicLevel) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<AcademicLevel> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getCode());
		query.setParameter(2, ((IAcademicLevelUpdateWrapper) updateWrapper).getCode());
	}

	@Override
	public void editQueryForDelete(Query query, AcademicLevel academicLevel) {
		query.setParameter(1, academicLevel.getCode());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IAcademicLevelDAO.COLUMN_CODE;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public AcademicLevel getResultValues(Serializable serializable) {
		AcademicLevel academicLevel = new AcademicLevel(((AcademicLevelDTO) serializable).getCode());

		return academicLevel;
	}
}
