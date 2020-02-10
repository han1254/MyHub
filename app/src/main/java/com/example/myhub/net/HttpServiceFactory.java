package com.example.myhub.net;

import com.example.base.networkfactory.retrofit.GetApiService;
import com.example.myhub.AppConfig;

/**
 * Time:2020/2/10 18:28
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class HttpServiceFactory {
    public static LoginService getLoginService() {
        return GetApiService.getApiService(LoginService.class, AppConfig.GITHUB_API_BASE_URL);
    }
}
