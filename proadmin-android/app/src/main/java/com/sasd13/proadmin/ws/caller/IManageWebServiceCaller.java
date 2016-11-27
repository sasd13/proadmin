package com.sasd13.proadmin.ws.caller;

/**
 * Created by ssaidali2 on 26/11/2016.
 */

public interface IManageWebServiceCaller extends IWebServiceCaller {

    void onCreated();

    void onUpdated();

    void onDeleted();
}
