package com.example.myhub.mvvm.bean;

/**
 * Time:2020/2/10 22:23
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function: 测试用的，不要管这个东西
 */

import com.google.gson.annotations.SerializedName;
public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
