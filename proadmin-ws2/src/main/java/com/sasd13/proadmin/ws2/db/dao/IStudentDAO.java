package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;

public interface IStudentDAO {

	StudentDTO create(Student student);

	void update(IStudentUpdateWrapper updateWrapper);

	void delete(Student student);

	List<StudentDTO> read(Map<String, String[]> parameters);
}
