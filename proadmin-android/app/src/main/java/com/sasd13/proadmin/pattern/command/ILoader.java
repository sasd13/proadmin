package com.sasd13.proadmin.pattern.command;

/**
 * Created by Samir on 16/01/2016.
 */
public interface ILoader {

    void onLoading();

    void onLoadSucceeded();

    void onLoadFailed();
}
