package com.example.base.networkfactory.base;

/**
 * Time:2020/1/25 17:42
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class Resource<T> {

    private NetWorkStatus status;
    private T data;
    private String message;

    private Resource(NetWorkStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public NetWorkStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(NetWorkStatus.DONE, data, null);
    }

    public static <T> Resource<T> error(String msg, T data) {
        return new Resource<>(NetWorkStatus.FAILED, data, msg);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(NetWorkStatus.LOADING, data, null);
    }

    public static <T> Resource<T> noNetwork() {
        return new Resource<>(NetWorkStatus.NO_NETWORK, null, null);
    }

    public static <T> Resource<T> noResponse() {
        return new Resource<>(NetWorkStatus.NO_RESPONSE, null, null);
    }
}
