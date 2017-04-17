package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.service.ServiceResult;

import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IUserService {

    ServiceResult<User> find(String userID);
}
