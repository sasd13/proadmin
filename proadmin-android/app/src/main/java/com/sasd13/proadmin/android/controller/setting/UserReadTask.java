package com.sasd13.proadmin.android.controller.setting;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.service.v1.IUserService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.EnumParameter;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserReadTask extends ReadRequestorTask {

    private SettingController controller;
    private IUserService service;

    public UserReadTask(SettingController controller, IUserService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.find(parameters.get(EnumParameter.USERID.getName())[0]);
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
