package com.sasd13.proadmin.android.service.v1;

import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.bean.update.UserUpdate;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IUserService {

    ServiceResult<User> find(String userID);

    ServiceResult<Void> update(UserUpdate userUpdate);
}
