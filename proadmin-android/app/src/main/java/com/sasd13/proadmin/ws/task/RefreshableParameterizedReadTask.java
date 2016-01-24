package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.IRefreshable;

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

        refreshable.displayLoad();
    }

    @Override
    protected void doInTaskCompleted() {
        super.doInTaskCompleted();

        refreshable.displayContent();
    }

    @Override
    protected void doInTaskError() {
        super.doInTaskError();

        refreshable.displayNotFound();
    }
}
