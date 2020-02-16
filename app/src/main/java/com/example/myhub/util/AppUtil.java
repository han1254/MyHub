package com.example.myhub.util;

import java.util.Locale;

import androidx.annotation.NonNull;

/**
 * Time:2020/2/14 15:25
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class AppUtil {

    @NonNull
    public static Locale getLocale(String language) {
        Locale locale;
        String[] array = language.split("-");
        if (array.length > 1) {
            //zh-rCN, zh-rTW", pt-rPT, etc... remove the 'r'
            String country =  array[1].replaceFirst("r", "");
            locale = new Locale(array[0], country);
        } else {
            locale = new Locale(language);
        }
        return locale;
    }


}
