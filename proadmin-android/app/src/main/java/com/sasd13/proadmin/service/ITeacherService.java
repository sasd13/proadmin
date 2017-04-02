package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeacherService {

    ServiceResult<List<Teacher>> read(Map<String, String[]> parameters);

    ServiceResult<Void> update(TeacherUpdateWrapper teacherUpdateWrapper);
}
