package com.sasd13.proadmin.android.component;

import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.util.message.IMessageHandler;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IController extends IMessageHandler {

    Scope getScope();
}
