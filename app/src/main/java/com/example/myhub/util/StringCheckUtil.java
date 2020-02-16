package com.example.myhub.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.example.myhub.HubApp;
import com.example.myhub.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * Time:2020/2/13 22:16
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class StringCheckUtil {

    public final static String LANGUAGE = "language";
    private final static Map<Locale, String> DATE_REGEX_MAP = new HashMap<>();
    static {
        DATE_REGEX_MAP.put(Locale.CHINA, "yyyy-MM-dd");
        DATE_REGEX_MAP.put(Locale.TAIWAN, "yyyy-MM-dd");
        DATE_REGEX_MAP.put(Locale.ENGLISH, "MMM d, yyyy");
        DATE_REGEX_MAP.put(Locale.GERMAN, "dd.MM.yyyy");
        DATE_REGEX_MAP.put(Locale.GERMANY, "dd.MM.yyyy");
    }


    public static boolean isNullString(String content) {
        return !((content != null) && !("".equals(content)));
    }

    public static String getEditContent(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean checkEditContentIsNull(EditText editText) {
        return isNullString(getEditContent(editText));
    }

    public static String getNewsTimeStr(@NonNull Context context, @NonNull Date date){
        long subTime = System.currentTimeMillis() - date.getTime();
        final double MILLIS_LIMIT = 1000.0f;
        final double SECONDS_LIMIT = 60 * MILLIS_LIMIT;
        final double MINUTES_LIMIT = 60 * SECONDS_LIMIT;
        final double HOURS_LIMIT = 24 * MINUTES_LIMIT;
        final double DAYS_LIMIT = 30 * HOURS_LIMIT;
        if(subTime < MILLIS_LIMIT){
            return context.getString(R.string.just_now);
        } else if(subTime < SECONDS_LIMIT){
            return Math.round(subTime / MILLIS_LIMIT) + " " + context.getString(R.string.seconds_ago);
        } else if(subTime < MINUTES_LIMIT){
            return Math.round(subTime / SECONDS_LIMIT) + " " + context.getString(R.string.minutes_ago);
        } else if(subTime < HOURS_LIMIT){
            return Math.round(subTime / MINUTES_LIMIT) + " " + context.getString(R.string.hours_ago);
        } else if(subTime < DAYS_LIMIT){
            return Math.round(subTime / HOURS_LIMIT) + " " + context.getString(R.string.days_ago);
        } else
            return getDateStr(date);
    }

    public static String getDateStr(@NonNull Date date){
        Locale locale = AppUtil.getLocale(getLanguage());
        String regex = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(regex, locale);
        return format.format(date);
    }

    public static String getLanguage() {
        return PreferenceManager.getDefaultSharedPreferences(HubApp.getApplication()).getString(LANGUAGE, "en");
    }

}
