package com.example.base.networkfactory.rx;

import android.net.ParseException;
import android.util.Log;

import com.example.base.networkfactory.exception.NetWorkException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * Time:2020/1/23 21:51
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class DefaultObserver<T> implements Observer<T> {

    static MutableLiveData<NetWorkException> mNetWorkException = new MutableLiveData<>();

    static {
        mNetWorkException.postValue(NetWorkException.NO_ERROR);
    }

    private NetWorkExceptionController controller;

    public DefaultObserver(NetWorkExceptionController controller) {

        this.controller = controller;
    }

    @Override
    public void onNext(T t) {
        Log.d("SUCCESS:", "网络成功");
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {

       if (e instanceof HttpException) {
           onException(NetWorkException.BAD_NETWORK);
           controller.badNetWorkError();
       } else if (e instanceof ConnectException
       || e instanceof UnknownHostException) {
           onException(NetWorkException.CONNECT_ERROR);
           controller.connectError();
       } else if (e instanceof InterruptedIOException) {
           onException(NetWorkException.CONNECT_TIMEOUT);
           controller.timeOutError();
       } else if (e instanceof JsonParseException
       || e instanceof JSONException
       || e instanceof ParseException) {
           onException(NetWorkException.PARSE_ERROR);
           controller.parseError();
       } else {
           onException(NetWorkException.UNKNOWN_ERROR);
           controller.unknownError();
       }
    }

    public void onException(NetWorkException status) {
        switch (status) {
            case BAD_NETWORK:
                Log.d("BAD_NETWORK_ERROR:", "onException: 网络状态较差");
            case CONNECT_ERROR:
                Log.d("CONNECT_ERROR:", "onException: 连接问题");
                break;
            case CONNECT_TIMEOUT:
                Log.d("TIMEOUT_ERROR:", "onException: 连接超时");
                break;
            case PARSE_ERROR:
                Log.d("PARSE_ERROR:", "onException: 解析错误");
            default:
                Log.d("UNKNOWN_ERROR:", "onException: 未知错误");
                break;
        }
    }


}
