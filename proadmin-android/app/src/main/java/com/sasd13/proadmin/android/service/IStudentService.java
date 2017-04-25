package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IStudentService {

    ServiceResult<List<StudentTeam>> read(Map<String, String[]> parameters);

    ServiceResult<List<Student>> readStudents(Map<String, String[]> parameters);

    ServiceResult<Void> create(Student student);

    ServiceResult<Void> create(StudentTeam studentTeam);

    ServiceResult<Void> update(StudentUpdate studentUpdate);

    ServiceResult<Void> delete(List<StudentTeam> studentTeams);
}
