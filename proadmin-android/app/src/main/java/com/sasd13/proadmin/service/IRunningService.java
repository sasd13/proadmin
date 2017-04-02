package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.Running;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IRunningService {

    ServiceResult<List<Running>> read(Map<String, String[]> parameters);

    ServiceResult<Void> create(Running running);

    ServiceResult<Void> delete(Running[] runnings);
}
