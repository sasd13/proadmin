package com.sasd13.proadmin.ws.caller;

import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

/**
 * Created by ssaidali2 on 26/11/2016.
 */

public interface IReadWebServiceCaller<T> extends IWebServiceCaller {

    void onRead(IReadWrapper<T> wrapper);
}
