package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.javaex.service.IServiceCaller;
import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public abstract class RESTWebService implements IHttpCallback {

    protected Context context;
    protected String url;
    protected IServiceCaller serviceCaller;

    public RESTWebService(Context context, String url, IServiceCaller serviceCaller) {
        this.context = context;
        this.url = url;
        this.serviceCaller = serviceCaller;
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(context.getResources().getString(R.string.error_ws_server_connection));
    }
}
