package com.sasd13.proadmin.service;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    private boolean success;
    private int httpCode;
    private T result;

    public ServiceResult(boolean success, int httpCode, T result) {
        this.success = success;
        this.httpCode = httpCode;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public T getResult() {
        return result;
    }
}
