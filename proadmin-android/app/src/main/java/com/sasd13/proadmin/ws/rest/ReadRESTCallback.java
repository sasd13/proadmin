package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.ws.IReadWebService;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class ReadRESTCallback<T> extends RESTCallback {

    private Class<T> mClass;
    private ReadTask<T> readTask;

    public ReadRESTCallback(Context context, String url, IReadWebService<List<T>> webService, Class<T> mClass) {
        super(context, url, webService);
        this.mClass = mClass;
    }

    public void read(Map<String, String[]> parameters) {
        readTask = new ReadTask<>(url, this, mClass);

        readTask.setParameters(parameters);
        readTask.execute();
    }

    public void readAll() {
        readTask = new ReadTask<>(url, this, mClass);

        readTask.execute();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(context, webService, readTask.getResponseErrors());
        } else {
            try {
                ((IReadWebService<List<T>>) webService).onRead(readTask.get());
            } catch (InterruptedException e) {
                webService.onError(context.getResources().getString(R.string.error_ws_exception_interrupted));
            } catch (ExecutionException e) {
                webService.onError(context.getResources().getString(R.string.error_ws_exception_execution));
            } catch (IndexOutOfBoundsException e) {
                webService.onError(context.getResources().getString(R.string.error_no_data));
            }
        }
    }
}
