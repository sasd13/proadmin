package com.sasd13.proadmin.ws2.db.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws2.db.dao.AbstractDAO;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDAO extends AbstractDAO implements ITeacherDAO {

	private static final Logger LOG = Logger.getLogger(TeacherDAO.class);

	public TeacherDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void create(Teacher teacher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String number, Teacher teacher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String number) {
		// TODO Auto-generated method stub

	}

	@Override
	public Teacher read(String number) {
		TeacherDTO teacherDTO = (TeacherDTO) currentSession()
				.createQuery("SELECT t from teachers where t.number = :number")
				.setParameter("number", number)
				.getSingleResult();

		return teacherDTO.toBean();
	}

	@Override
	public Teacher readByEmail(String email) {
		TeacherDTO teacherDTO = (TeacherDTO) currentSession()
				.createQuery("SELECT t from teachers where t.email = :email")
				.setParameter("email", email)
				.getSingleResult();

		return teacherDTO.toBean();
	}

}
