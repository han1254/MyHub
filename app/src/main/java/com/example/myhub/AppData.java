package com.example.myhub;

import android.appwidget.AppWidgetProvider;

import com.example.base.utils.LiveDataUtil;
import com.example.myhub.databinding.ItemAvatarBinding;
import com.example.myhub.mvvm.bean.User;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import okhttp3.MultipartBody;

/**
 * Time:2020/2/11 14:29
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class AppData {

    private User appCurrentUser;
    private MutableLiveData<User> appUserLiveData;
    private String dialogInfo;

    private AppData() {
        appUserLiveData = new MutableLiveData<>();
        dialogInfo = "<p>这是我用一周的时间为大家写的demo，因为还有别的事情要忙，所以写的很急。多多包涵</p><p><font color = #23bdb1><i>这个demo主要是调用了github的接口，最难的地方应该是登陆功能，提示一点，我使用的是github的basic auth登陆，大家可以去查一下如何申请Auth App，获得ID和密码，使用方法我代码里有<br>代码我会共享，但是这个里面用到的技术实在有点新，没有太大的参考价值，能看懂逻辑就行，没必要一定要按照我的写</i></font></p><p>主要功能点有：</p><ul><b><li>登陆</li><li>获取新闻</li><li>搜索</li><li>展示个人界面</li><li>分享</li><li>超链接跳转</li><li>登陆之后的数据保存(保存到手机数据库)</li><li>如果有数据则自动登陆</li><li>退出登陆后数据销毁</li></b></ul>";
    }

    private static final class AppDataHolder {

        private static final AppData INSTANCE = new AppData();
    }

    public static AppData getInstance() {
        return AppDataHolder.INSTANCE;
    }


    public String getDialogInfo() {
        return dialogInfo;
    }
    public void setAppCurrentUser(User user) {
        this.appCurrentUser = user;
        LiveDataUtil.setValue(appUserLiveData, user);
    }

    public User getAppCurrentUser() {
        return this.appCurrentUser;
    }

    public MutableLiveData<User> getAppUserLiveData() {
        return appUserLiveData;
    }
}
