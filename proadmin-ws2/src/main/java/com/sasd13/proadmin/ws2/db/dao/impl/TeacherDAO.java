package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.orm.HibernateDAO;
import com.sasd13.javaex.orm.HibernateUtils;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDAO extends HibernateDAO<Teacher> implements ITeacherDAO {

	private static final Logger LOG = Logger.getLogger(TeacherDAO.class);

	public TeacherDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void create(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);
		
		currentSession().save(teacherDTO);
		currentSession().flush();
	}
	
	@Override
	public void update(Teacher t, Map<String, String> parameters) throws HibernateException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE teachers SET ");
		builder.append("_code= :code");
		builder.append("AND _firstname= :firstname");
		builder.append("AND _lastname= :firstname");
		builder.append("AND _firstname= :firstname");
		
		
		currentSession().createQuery("UPDATE teachers SET"
				+ ")
	}
	
	@Override
	public void delete(Map<String, String[]> parameters) throws HibernateException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> readAll() throws HibernateException {
		return HibernateUtils.readAll(currentSession(), TeacherDTO.TABLE);
	}

	@Override
	public Teacher readByNumber(String number) {
		TeacherDTO teacherDTO = (TeacherDTO) currentSession()
				.createQuery("SELECT t from teachers where t.number = :number")
				.setParameter("number", number)
				.getSingleResult();

		return teacherDTO.toBean();
	}

	@Override
	public Teacher readByEmail(String email) {
		TeacherDTO teacherDTO = (TeacherDTO) currentSession()
				.createQuery("SELECT t FROM teachers WHERE t.email = :email")
				.setParameter("email", email)
				.getSingleResult();

		return teacherDTO.toBean();
	}

}
