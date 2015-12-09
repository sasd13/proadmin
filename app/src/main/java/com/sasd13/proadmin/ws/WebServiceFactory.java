package com.sasd13.proadmin.ws;

/**
 * Created by Samir on 09/12/2015.
 */
public class WebServiceFactory {

    public static WebService get(String service) {
        if ("PROJECT".equals(service)) {
            return new ProjectWebService();
        }

        return null;
    }
}
