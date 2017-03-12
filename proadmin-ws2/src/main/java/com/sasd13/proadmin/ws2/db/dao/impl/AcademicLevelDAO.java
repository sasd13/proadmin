/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AcademicLevelDAO extends AbstractDAO implements IAcademicLevelDAO {

	public AcademicLevelDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcademicLevelDTO> readAll() {
		CriteriaQuery<AcademicLevelDTO> criteria = currentSession().getCriteriaBuilder().createQuery(AcademicLevelDTO.class);
		Query query = currentSession().createQuery(criteria);

		return (List<AcademicLevelDTO>) query.getResultList();
	}
}
