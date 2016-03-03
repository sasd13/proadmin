package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.pattern.command.IRefreshable;

/**
 * Created by Samir on 24/12/2015.
 */
public class RefreshableUpdateTask<T> extends UpdateTask<T> {

    protected IRefreshable refreshable;

    public RefreshableUpdateTask(Context context, Class<T> mClass, IRefreshable refreshable) {
        super(context, mClass);

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
