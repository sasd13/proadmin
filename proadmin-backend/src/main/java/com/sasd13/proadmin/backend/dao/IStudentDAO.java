package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;

public interface IStudentDAO {

	StudentDTO create(Student student);

	void update(Student student);

	void delete(Student student);

	StudentDTO read(String intermediary);

	List<StudentDTO> read(Map<String, String[]> parameters);
}
