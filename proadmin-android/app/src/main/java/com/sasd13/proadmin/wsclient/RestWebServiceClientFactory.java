package com.sasd13.proadmin.wsclient;

import com.sasd13.javaex.wsclient.RestWebServiceClient;

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
