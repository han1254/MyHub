package com.example.myhub.data;

import com.example.myhub.mvvm.bean.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import retrofit2.http.DELETE;

/**
 * Time:2020/2/11 15:13
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user ORDER BY datetime(login_date)")
    List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

}
