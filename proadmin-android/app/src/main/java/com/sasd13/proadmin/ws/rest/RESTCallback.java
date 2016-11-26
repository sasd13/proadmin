package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.javaex.ws.IWebService;
import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public abstract class RESTCallback implements IHttpCallback {

    protected Context context;
    protected String url;
    protected IWebService webService;

    public RESTCallback(Context context, String url, IWebService webService) {
        this.context = context;
        this.url = url;
        this.webService = webService;
    }

    @Override
    public void onLoad() {
        webService.onPreExecute();
    }

    @Override
    public void onFail(int httpResponseCode) {
        webService.onError(context.getResources().getString(R.string.error_ws_server_connection));
    }
}
