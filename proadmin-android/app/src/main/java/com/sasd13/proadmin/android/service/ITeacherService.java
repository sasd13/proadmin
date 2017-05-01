package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeacherService {

    ServiceResult<List<Teacher>> read(Map<String, Object> criterias);

    ServiceResult<Void> create(Teacher teacher);

    ServiceResult<Void> update(Teacher teacher);
}
