package com.example.myhub.net;

import com.example.myhub.mvvm.bean.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Time:2020/2/11 10:16
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface UserService {

    @NonNull
    @GET("user")
    Observable<Response<User>> getPersonInfo(
            @Header("forceNetWork") boolean forceNetWork
    );



}

