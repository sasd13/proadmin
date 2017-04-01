package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.Running;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IRunningService {

    List<Running> read(Map<String, String[]> parameters);

    void create(Running running);

    void delete(Running running);
}
