package com.sasd13.proadmin.ws;

import com.sasd13.androidx.ws.WSConsumer;

/**
 * Created by Samir on 09/12/2015.
 */
public class WSConsumerFactory {

    public static WSConsumer make(String service) {
        if ("PROJECT".equals(service)) {
            return new ProjectWSConsumer();
        }

        return null;
    }
}
