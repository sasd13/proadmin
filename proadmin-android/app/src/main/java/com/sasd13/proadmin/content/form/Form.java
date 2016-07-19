package com.sasd13.proadmin.content.form;

import android.content.Context;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public abstract class Form {

    protected Context context;
    protected RecyclerHolder holder;

    public Form(Context context) {
        this.context = context;
        holder = new RecyclerHolder();
    }

    public RecyclerHolder getHolder() {
        return holder;
    }
}
