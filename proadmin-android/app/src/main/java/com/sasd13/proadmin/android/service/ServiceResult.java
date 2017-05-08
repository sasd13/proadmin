package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.util.EnumError;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    public static final ServiceResult NULL = new ServiceResult<>(false, EnumError.UNKNOWN.getCode());

    private boolean success;
    private int httpStatus;
    private Map<String, String> errors;
    private T data;

    public ServiceResult(boolean success, int httpStatus) {
        this.success = success;
        this.httpStatus = httpStatus;
        errors = Collections.emptyMap();
    }

    public ServiceResult(boolean success, int httpStatus, Map<String, String> errors, T data) {
        this.success = success;
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }
}
