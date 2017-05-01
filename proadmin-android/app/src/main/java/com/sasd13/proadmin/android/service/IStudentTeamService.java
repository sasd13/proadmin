package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IStudentTeamService {

    ServiceResult<List<StudentTeam>> read(Map<String, Object> criterias);

    ServiceResult<Void> create(StudentTeam studentTeam);

    ServiceResult<Void> delete(List<StudentTeam> studentTeams);
}
