package com.example.myhub.net;

import com.example.base.networkfactory.retrofit.GetApiService;
import com.example.myhub.AppConfig;
import com.example.myhub.AppData;
import com.example.myhub.mvvm.model.login.GetUserModel;

/**
 * Time:2020/2/10 18:28
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class HttpServiceFactory {

    public static LoginService getLoginService(String token) {
        return GetApiService.getApiService(LoginService.class, AppConfig.GITHUB_API_BASE_URL, token);
    }

    public static GitHubClient getClientService() {
        return GetApiService.getApiService(GitHubClient.class, "https://github.com");
    }

    public static UserService getUserService(String accessToken) {
        return GetApiService.getApiService(UserService.class,AppConfig.GITHUB_API_BASE_URL, accessToken);
    }

    public static ContentService getContentService() {
        return GetApiService.getApiService(ContentService.class, AppConfig.GITHUB_API_BASE_URL, AppData.getInstance().getAppCurrentUser().getToken());
    }
}
