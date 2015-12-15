package com.sasd13.proadmin.ws;

import com.sasd13.androidx.ws.RestWebServiceClient;

/**
 * Created by Samir on 09/12/2015.
 */
public class RestWebServiceClientFactory {

    public static RestWebServiceClient make(String service) {
        if ("PROJECT".equals(service)) {
            return new ProjectRestWebServiceClient();
        }

        return null;
    }
}
