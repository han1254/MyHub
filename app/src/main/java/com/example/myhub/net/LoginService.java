package com.example.myhub.net;

import com.example.myhub.mvvm.bean.BasicToken;
import com.example.myhub.net.model.AuthRequestModel;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Time:2020/2/10 19:59
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    Observable<Response<BasicToken>> authorizations(
            @NonNull @Body AuthRequestModel authRequestModel
    );

}
