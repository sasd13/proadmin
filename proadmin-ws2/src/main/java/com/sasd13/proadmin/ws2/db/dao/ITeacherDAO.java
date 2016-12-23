package com.sasd13.proadmin.ws2.db.dao;

import org.hibernate.HibernateException;

import com.sasd13.javaex.orm.IDAO;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherDAO extends IDAO<Teacher> {

	Teacher readByNumber(String number) throws HibernateException;

	Teacher readByEmail(String email) throws HibernateException;
}
