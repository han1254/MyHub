package com.example.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author YangZhaoxin.
 * @since 2020/1/28 11:01.
 * email yangzhaoxin@hrsoft.net.
 */

public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, String msg) {
        if (context != null && !TextUtils.isEmpty(msg)) {
            if (mToast != null) {
                // TODO: Toast的封装原因和方法，我：一直只用一个对象
                // TODO: 什么时候需要application context对象，全局Toast是否有必要
                mToast.cancel();
            }
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            mToast.setText(msg);
            mToast.show();
        }
    }
}
