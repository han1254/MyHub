package com.example.base.viewmodel;

import com.example.base.model.SuperBaseModel;

import java.util.Map;

/**
 * @author YangZhaoxin.
 * @since 2020/1/26 16:38.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IMvvmNetworkViewModel extends IMvvmBaseViewModel {

//    void attachUI(V view);
//
//    V getPageView();
//
//    boolean isUIAttached();
//
    void detachModels();

    Map<String, SuperBaseModel> getModels();
}
