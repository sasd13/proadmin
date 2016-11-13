package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;
import com.sasd13.proadmin.util.wrapper.read.member.StudentReadWrapper;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class StudentReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<Student>> serviceCaller;
    private ReadTask<Student> readTask;

    public StudentReadService(IReadServiceCaller<IReadWrapper<Student>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void read(String number) {
        readTask = new ReadTask<>(WSResources.URL_WS_STUDENTS, this, Student.class);

        readTask.putParameter(EnumParameter.NUMBER.getName(), new String[]{number});
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(new StudentReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
