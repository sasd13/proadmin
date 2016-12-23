package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherDAO {

	void create(Teacher teacher);

	void update(String number, Teacher teacher);

	void delete(String number);

	Teacher read(String number);

	Teacher readByEmail(String email);
}
