package com.example.myhub.data;

import android.content.Context;

import com.example.myhub.AppData;
import com.example.myhub.mvvm.bean.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Time:2020/2/11 15:37
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    private static final Object o = new Object();
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public abstract UserDao getUserDao();

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (o) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context
                    , AppDataBase.class, "my_hub_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
