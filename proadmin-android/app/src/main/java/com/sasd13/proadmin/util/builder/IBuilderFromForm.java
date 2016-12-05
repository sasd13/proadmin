package com.sasd13.proadmin.util.builder;

import com.sasd13.androidex.gui.form.FormException;

/**
 * Created by ssaidali2 on 22/11/2016.
 */
public interface IBuilderFromForm<T> {

    T build() throws FormException;
}