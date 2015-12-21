package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.javaex.db.EntityDAO;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;

public interface StudentDAO extends EntityDAO<Student> {
	
	String STUDENT_TABLE_NAME = "students";
	
	String STUDENT_ID = "student_id";
	String STUDENT_NUMBER = "student_number";
	String STUDENT_ACADEMICLEVEL = "student_academiclevel";
	String STUDENT_FIRSTNAME = "student_firstname";
	String STUDENT_LASTNAME = "student_lastname";
	String STUDENT_EMAIL = "student_email";
	
	Student selectByNumber(String number);
	
	List<Student> selectByAcademicLevel(AcademicLevel academicLevel);
	
	List<Student> selectByEmail(String email);
}
