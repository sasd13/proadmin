package com.sasd13.proadmin.android.service.v1;

import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IProjectService {

    ServiceResult<List<Project>> readAll();
}
