package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.androidex.ws.rest.callback.ManageRESTCallback;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.ws.IManageWebService;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.caller.IManageWebServiceCaller;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ManageService<T> implements IManageWebService {

    private Context context;
    private IManageWebServiceCaller caller;
    protected ManageRESTCallback<T> callback;

    public ManageService(Context context, String url, IManageWebServiceCaller caller) {
        this.context = context;
        this.caller = caller;
        callback = new ManageRESTCallback<>(url, this);
    }

    public void create(T t) {
        callback.create(t);
    }

    public void update(IUpdateWrapper<T> wrapper) {
        callback.update(wrapper);
    }

    public void delete(T t) {
        callback.delete(t);
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onCreated() {
        caller.onCreated();
    }

    @Override
    public void onUpdated() {
        caller.onUpdated();
    }

    @Override
    public void onDeleted() {
        caller.onDeleted();
    }

    @Override
    public void onError(List<String> errors) {
        WebServiceUtils.handleErrors(context, caller, errors);
    }
}
