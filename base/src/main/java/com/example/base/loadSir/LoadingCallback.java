package com.example.base.loadSir;

import android.content.Context;
import android.view.View;

import com.example.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author YangZhaoxin.
 * @since 2020/1/11 11:15.
 * email yangzhaoxin@hrsoft.net.
 */

public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
