package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

public interface ITeacherDAO {

	TeacherDTO create(Teacher teacher);

	void update(ITeacherUpdateWrapper updateWrapper);

	void delete(Teacher teacher);

	List<TeacherDTO> read(Map<String, String[]> parameters);
}
