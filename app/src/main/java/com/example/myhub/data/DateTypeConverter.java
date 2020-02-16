package com.example.myhub.data;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Time:2020/2/11 15:29
 * Author: han1254
 * Email: 1254763408@qq.comx
 * Function:
 */
public class DateTypeConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
