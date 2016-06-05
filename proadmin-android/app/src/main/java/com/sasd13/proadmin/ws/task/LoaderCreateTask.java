package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.util.ILoader;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoaderCreateTask<T> extends CreateTask<T> {

    protected ILoader loader;

    public LoaderCreateTask(Context context, Class<T> mClass, ILoader loader) {
        super(context, mClass);

        this.loader = loader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loader.onLoading();
    }

    @Override
    protected void onTaskSucceeded() {
        super.onTaskSucceeded();

        loader.onLoadSucceeded();
    }

    @Override
    protected void onTaskFailed() {
        super.onTaskFailed();

        loader.onLoadFailed();
    }
}
