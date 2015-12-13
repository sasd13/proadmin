package com.sasd13.proadmin.ws;

/**
 * Created by Samir on 09/12/2015.
 */
public class WebServiceProviderFactory {

    public static WebServiceProvider get(String service) {
        if ("PROJECT".equals(service)) {
            return new ProjectWebServiceProvider();
        }

        return null;
    }
}
