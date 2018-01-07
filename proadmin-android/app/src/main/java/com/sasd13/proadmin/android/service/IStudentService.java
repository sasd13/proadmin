package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IStudentService {

    ServiceResult<List<Student>> read(Map<String, Object> criterias);

    ServiceResult<Void> create(Student student);

    ServiceResult<Void> update(Student student);
}
