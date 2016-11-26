package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;
import com.sasd13.proadmin.ws.wrapper.member.StudentTeamReadWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentTeamReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<StudentTeam>> serviceCaller;
    private ReadTask<StudentTeam> readTask;

    public StudentTeamReadService(IReadServiceCaller<IReadWrapper<StudentTeam>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void read(Team team) {
        readTask = new ReadTask<>(WSResources.URL_WS_STUDENTTEAMS, this, StudentTeam.class);

        readTask.addRequestHeader(EnumHttpHeader.READ_CODE.getName(), Constants.WS_REQUEST_READ_DEEP);
        readTask.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(new StudentTeamReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_no_data);
            }
        }
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}