package com.example.base.recyclerview_databinding.paingdatasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * Time:2020/1/28 21:25
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class PagingFactory<K, V> extends DataSource.Factory<K, V> {

    @NonNull
    @Override
    public DataSource<K, V> create() {
        return null;
    }

    public abstract PagingType getPagingType();

    class BasePageKeyedDataSource extends PageKeyedDataSource<K, V> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<K> params, @NonNull LoadInitialCallback<K, V> callback) {

        }

        @Override
        public void loadBefore(@NonNull LoadParams<K> params, @NonNull LoadCallback<K, V> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<K> params, @NonNull LoadCallback<K, V> callback) {

        }
    }

    class BaseItemKeyedDataSource extends ItemKeyedDataSource<K, V> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<K> params, @NonNull LoadInitialCallback<V> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<K> params, @NonNull LoadCallback<V> callback) {

        }

        @Override
        public void loadBefore(@NonNull LoadParams<K> params, @NonNull LoadCallback<V> callback) {

        }

        @NonNull
        @Override
        public K getKey(@NonNull V item) {
            return null;
        }
    }

}
