package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.StudentDTO;

public interface IStudentDAO {

	StudentDTO create(Student student);

	void update(List<StudentUpdateWrapper> updateWrappers);

	void delete(List<Student> students);

	List<StudentDTO> read(Map<String, String[]> parameters);
}
