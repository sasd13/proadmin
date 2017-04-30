package com.sasd13.proadmin.android.service.v1;

import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IStudentService {

    ServiceResult<List<Student>> read(Map<String, String[]> parameters);

    ServiceResult<Void> create(Student student);

    ServiceResult<Void> update(StudentUpdate studentUpdate);
}
