package com.sasd13.proadmin.ws.task;

import android.content.Context;

import com.sasd13.proadmin.pattern.command.ILoader;

import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoaderParameterizedReadTask<T> extends ParameterizedReadTask<T> {

    protected ILoader loader;

    public LoaderParameterizedReadTask(Context context, Class<T> mClass, Map<String, String[]> parameters, ILoader loader) {
        super(context, mClass, parameters);

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
