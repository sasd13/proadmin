package com.sasd13.proadmin.service;

import com.sasd13.proadmin.util.error.EnumError;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    public static final ServiceResult NULL = new ServiceResult<>(false, EnumError.UNKNOWN.getCode(), null);

    private boolean success;
    private int httpStatus;
    private T result;

    public ServiceResult(boolean success, int httpStatus, T result) {
        this.success = success;
        this.httpStatus = httpStatus;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public T getResult() {
        return result;
    }
}
