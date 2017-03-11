/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.wrapper.update.AcademicLevelUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AcademicLevelDAO extends AbstractDAO implements IAcademicLevelDAO {

	public AcademicLevelDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public AcademicLevelDTO insert(AcademicLevel academicLevel) {
		AcademicLevelDTO dto = new AcademicLevelDTO(academicLevel);

		currentSession().saveOrUpdate(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(AcademicLevelUpdateWrapper updateWrapper) {
		AcademicLevelDTO dto = select(updateWrapper.getCode());

		dto.setCode(updateWrapper.getWrapped().getCode());

		currentSession().saveOrUpdate(dto);
		currentSession().flush();
	}

	@Override
	public void delete(AcademicLevel academicLevel) {
		AcademicLevelDTO dto = select(academicLevel.getCode());

		currentSession().remove(dto);
		currentSession().flush();
	}

	@Override
	public AcademicLevelDTO select(String code) {
		Query query = currentSession().createQuery("SELECT * FROM academiclevels al WHERE al.code := code");
		query.setParameter("code", code);

		return (AcademicLevelDTO) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcademicLevelDTO> selectAll() {
		Query query = currentSession().createQuery("SELECT * FROM academiclevels");

		return (List<AcademicLevelDTO>) query.getResultList();
	}
}
