package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IRunningService {

    ServiceResult<List<Running>> read(Map<String, Object> criterias);

    ServiceResult<Void> create(Running running);

    ServiceResult<Void> delete(List<Running> runnings);
}
