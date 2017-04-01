package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IStudentService {

    List<Student> read(Map<String, String[]> parameters);

    void create(Student student);

    void create(StudentTeam studentTeam);

    void update(StudentUpdateWrapper studentUpdateWrapper);

    void delete(StudentTeam studentTeam);
}
