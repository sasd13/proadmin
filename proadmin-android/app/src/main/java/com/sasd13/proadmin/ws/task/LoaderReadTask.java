package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.pattern.command.ILoader;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoaderReadTask<T> extends ReadTask<T> {

    protected ILoader loader;

    public LoaderReadTask(Context context, Class<T> mClass, ILoader loader) {
        super(context, mClass);

        this.loader = loader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loader.doInLoad();
    }

    @Override
    protected void doInTaskCompleted() {
        super.doInTaskCompleted();

        loader.doInCompleted();
    }

    @Override
    protected void doInTaskError() {
        super.doInTaskError();

        loader.doInError();
    }
}
