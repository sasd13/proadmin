package com.sasd13.proadmin.android.service.v1;

import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IAuthenticationService {

    String PARAMETER_USERNAME = "PARAMETER_USERNAME";
    String PARAMETER_PASSWORD = "PARAMETER_PASSWORD";

    ServiceResult<Map<String, String>> logIn(Map<String, String> credentials);
}
