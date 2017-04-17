package com.sasd13.proadmin.service;

import com.sasd13.proadmin.util.EnumError;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    public static final ServiceResult NULL = new ServiceResult<>(false, EnumError.UNKNOWN.getCode(), null, Collections.EMPTY_MAP);

    private boolean success;
    private int httpStatus;
    private Map<String, List<String>> headers;
    private T result;

    public ServiceResult(boolean success, int httpStatus, Map<String, List<String>> headers, T result) {
        this.success = success;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public T getResult() {
        return result;
    }
}
