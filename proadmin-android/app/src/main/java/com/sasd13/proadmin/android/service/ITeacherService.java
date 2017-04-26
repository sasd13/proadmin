package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeacherService {

    ServiceResult<List<Teacher>> read(Map<String, String[]> parameters);

    ServiceResult<Void> create(Teacher teacher);

    ServiceResult<Void> update(TeacherUpdate teacherUpdate);
}
