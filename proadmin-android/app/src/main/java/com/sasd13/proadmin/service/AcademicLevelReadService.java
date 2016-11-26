package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.javaex.ws.IReadWebService;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.caller.IReadWebServiceCaller;
import com.sasd13.proadmin.ws.rest.ReadRESTCallback;
import com.sasd13.proadmin.ws.wrapper.AcademicLevelReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class AcademicLevelReadService implements IReadWebService<List<AcademicLevel>> {

    private IReadWebServiceCaller<List<AcademicLevel>> caller;
    private ReadRESTCallback<AcademicLevel> callback;

    public AcademicLevelReadService(Context context, IReadWebServiceCaller<List<AcademicLevel>> caller) {
        this.caller = caller;
        callback = new ReadRESTCallback<>(context, WSResources.URL_WS_ACADEMICLEVELS, this, AcademicLevel.class);
    }

    public void readAcademicLevels() {
        callback.readAll();
    }

    @Override
    public void onPreExecute() {
        caller.onWait();
    }

    @Override
    public void onRead(List<AcademicLevel> academicLevels) {
        caller.onRead(new AcademicLevelReadWrapper(academicLevels));
    }

    @Override
    public void onError(String error) {
        caller.onError(error);
    }
}
