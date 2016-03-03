package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.pattern.command.IRefreshable;

import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class RefreshableParameterizedReadTask<T> extends ParameterizedReadTask<T> {

    protected IRefreshable refreshable;

    public RefreshableParameterizedReadTask(Context context, Class<T> mClass, Map<String, String[]> parameters, IRefreshable refreshable) {
        super(context, mClass, parameters);

        this.refreshable = refreshable;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        refreshable.doInLoad();
    }

    @Override
    protected void doInTaskCompleted() {
        super.doInTaskCompleted();

        refreshable.doInCompleted();
    }

    @Override
    protected void doInTaskError() {
        super.doInTaskError();

        refreshable.doInError();
    }
}
