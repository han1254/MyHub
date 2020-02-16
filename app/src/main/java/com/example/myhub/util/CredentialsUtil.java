package com.example.myhub.util;

import java.io.UnsupportedEncodingException;

import okio.ByteString;

/**
 * Time:2020/2/11 11:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class CredentialsUtil {

    public static String basic(String userName, String password) {
        try {
            String usernameAndPassword = userName + ":" + password;
            byte[] bytes = usernameAndPassword.getBytes("ISO-8859-1");
            String encoded = ByteString.of(bytes).base64();
            return "Basic " + encoded;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
