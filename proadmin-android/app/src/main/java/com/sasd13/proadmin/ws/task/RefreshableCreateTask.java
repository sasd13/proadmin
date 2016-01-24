package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.IRefreshable;

/**
 * Created by Samir on 24/12/2015.
 */
public class RefreshableCreateTask<T> extends CreateTask<T> {

    protected IRefreshable refreshable;

    public RefreshableCreateTask(Context context, Class<T> mClass, IRefreshable refreshable) {
        super(context, mClass);

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
