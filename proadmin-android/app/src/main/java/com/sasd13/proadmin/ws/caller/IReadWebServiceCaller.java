package com.sasd13.proadmin.ws.caller;

import java.util.List;

/**
 * Created by ssaidali2 on 26/11/2016.
 */

public interface IReadWebServiceCaller<T> extends IWebServiceCaller {

    void onReaded(List<T> ts);
}
