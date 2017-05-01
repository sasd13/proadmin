package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IProjectService {

    ServiceResult<List<Project>> read(Map<String, Object> criterias);
}
