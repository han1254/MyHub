package com.example.myhub;

/**
 * Time:2020/2/10 17:52
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class AppConfig {

    public final static String OPENHUB_CLIENT_ID = BuildConfig.CLIENT_ID;

    public final static String OPENHUB_CLIENT_SECRET = BuildConfig.CLIENT_SECRET;

    public final static String GITHUB_BASE_URL = "https://github.com/";

    public final static String GITHUB_API_BASE_URL = "https://api.github.com/";

    public final static String OAUTH2_URL = GITHUB_BASE_URL + "login/oauth/authorize";

    public static final String REDIRECT_URL = "han1254://callback/";

    //设置过期时间（天）
    public static final int EXPIRE_DAY = 7;
}
