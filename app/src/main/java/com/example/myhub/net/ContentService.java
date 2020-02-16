package com.example.myhub.net;

import com.example.myhub.mvvm.bean.GitHubEvent;
import com.example.myhub.mvvm.bean.Repo;
import com.example.myhub.mvvm.bean.RepoSearchResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Time:2020/2/14 15:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface ContentService {
    @NonNull
    @GET("events")
    Observable<Response<ArrayList<GitHubEvent>>> getPublicEvent(
            @Header("forceNetWork") boolean forceNetWork,
            @Query("page") int page
    );

    @GET("search/repositories?sort=stars")
    Observable<Response<RepoSearchResponse>> getRepos(
            @Query("q") String query,
            @Query("page") int pge,
            @Query("per_page") int itemsPerPage);
}
