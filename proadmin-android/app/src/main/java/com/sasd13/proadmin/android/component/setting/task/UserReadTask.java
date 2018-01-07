package com.sasd13.proadmin.android.component.setting.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.setting.controller.SettingController;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserReadTask extends RequestorTask {

    private SettingController controller;
    private IUserService service;

    public UserReadTask(SettingController controller, IUserService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.find((String) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<User> result = (ServiceResult<User>) out;

        if (result.isSuccess()) {
            controller.onReadUser(result.getData());
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
