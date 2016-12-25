package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDAO extends HibernateSession<Teacher> implements ITeacherDAO {

	private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class);

	public TeacherDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(Teacher teacher) throws DAOException {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);

		HibernateUtils.create(this, teacherDTO);

		return teacherDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE teachers SET ");
		builder.append("_code= :code");
		builder.append("AND _firstname= :firstname");
		builder.append("AND _lastname= :firstname");
		builder.append("AND _firstname= :firstname");
	}

	@Override
	public void delete(Teacher teacher) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Teacher select(long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> select(Map<String, String[]> parameters) throws DAOException {
		return HibernateUtils.read(this, TABLE, parameters);
	}

	@Override
	public List<Teacher> selectAll() throws DAOException {
		return HibernateUtils.readAll(this, TABLE);
	}

	@Override
	public boolean contains(Teacher teacher) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Teacher> updateWrapper) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editQueryForDelete(Query query, Teacher teacher) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Teacher getResultValues(Serializable serializable) {
		Teacher teacher = new Teacher();
		TeacherDTO dto = (TeacherDTO) serializable;

		teacher.setNumber(dto.getCode());
		teacher.setFirstName(dto.getFirstName());
		teacher.setLastName(dto.getLastName());
		teacher.setEmail(dto.getEmail());

		return teacher;
	}
}
