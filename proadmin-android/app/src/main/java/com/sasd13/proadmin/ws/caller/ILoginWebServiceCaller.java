package com.sasd13.proadmin.ws.caller;

/**
 * Created by ssaidali2 on 26/11/2016.
 */

public interface ILoginWebServiceCaller<T> extends IWebServiceCaller {

    void onLoggedIn(T t);
}
