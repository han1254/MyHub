package com.example.base.view;

/**
 * @author YangZhaoxin.
 * @since 2020/1/26 16:28.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IBasePagingView extends IBaseView {

    /**
     * 加载更多时 加载失败
     * @param message
     */
    void onLoadMoreFailure(String message);

    /**
     * 加载更多时 已全部加载
     */
    void onLoadMoreEmpty();
}
