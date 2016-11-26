package com.sasd13.proadmin.ws.caller;

import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

/**
 * Created by ssaidali2 on 26/11/2016.
 */

public interface ILoginWebServiceCaller<T> extends IWebServiceCaller {

    void onLogin(IReadWrapper<T> wrapper);
}
