package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.service.IReadServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.ServiceCallerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class ReadRESTWebService<T> extends RESTWebService {

    private Class<T> mClass;
    private Map<String, String[]> parameters;
    private ReadTask<T> readTask;

    public ReadRESTWebService(Context context, String url, IReadServiceCaller<List<T>> serviceCaller, Class<T> mClass) {
        super(context, url, serviceCaller);
        this.mClass = mClass;
        parameters = new HashMap<>();
    }

    public void putParameter(String parameter, String[] values) {
        parameters.put(parameter, values);
    }

    public void read() {
        readTask = new ReadTask<>(url, this, mClass);

        readTask.setParameters(parameters);
        readTask.execute();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(context, serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                ((IReadServiceCaller<List<T>>) serviceCaller).onRead(readTask.get());
            } catch (InterruptedException e) {
                serviceCaller.onError(context.getResources().getString(R.string.error_ws_exception_interrupted));
            } catch (ExecutionException e) {
                serviceCaller.onError(context.getResources().getString(R.string.error_ws_exception_execution));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(context.getResources().getString(R.string.error_no_data));
            }
        }
    }
}
