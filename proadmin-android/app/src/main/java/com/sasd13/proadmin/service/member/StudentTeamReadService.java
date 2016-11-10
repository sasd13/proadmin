package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.wrapper.IStudentTeamReadWrapper;
import com.sasd13.proadmin.wrapper.impl.StudentTeamReadWrapper;

public class StudentTeamReadService implements IHttpCallback {

    private IReadServiceCaller<IStudentTeamReadWrapper> serviceCaller;
    private ReadTask<StudentTeam> readTask;

    public StudentTeamReadService(IReadServiceCaller<IStudentTeamReadWrapper> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readStudentTeams(String teamNumber) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGTEAMS, this, StudentTeam.class);

        readTask.putParameter(EnumParameter.TEAM.getName(), new String[]{teamNumber});
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
                serviceCaller.onReadSucceeded(new StudentTeamReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}