package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.androidex.ws.rest.callback.ReadRESTCallback;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.ws.IReadWebService;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.caller.IReadWebServiceCaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ReadService<T> implements IReadWebService<List<T>> {

    private Context context;
    private IReadWebServiceCaller<T> caller;
    private ReadRESTCallback<T> callback;
    private Map<String, String[]> headers;
    private Map<String, String[]> parameters;

    public ReadService(Context context, String url, IReadWebServiceCaller<T> caller, Class<T> mClass) {
        this.context = context;
        this.caller = caller;
        callback = new ReadRESTCallback<>(url, this, mClass);
        headers = new HashMap<>();
        parameters = new HashMap<>();
    }

    public void setDeepReadEnabled(boolean enabled) {
        if (enabled) {
            headers.put(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        } else {
            headers.remove(EnumHttpHeader.READ_CODE.getName());
        }
    }

    public void putParameters(String key, String[] values) {
        parameters.put(key, values);
    }

    public void read() {
        callback.setHeaders(headers);
        callback.setParameters(parameters);
        callback.read();
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onReaded(List<T> ts) {
        caller.onReaded(ts);
    }

    @Override
    public void onError(List<String> errors) {
        WebServiceUtils.handleErrors(context, caller, errors);
    }
}
