package com.zjxdd.mvp.iApiService;


import com.zjxdd.mvp.entity.BaseEnity;
import com.zjxdd.mvp.entity.PoetryEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:13
 */
public interface IGetPoetryEntity {
    @GET("all.json")
    Observable<PoetryEntity> getPoetry();

    @GET("login")
    Observable<BaseEnity> userLogin();
}

