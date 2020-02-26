package com.ldc.wandroid.net;

import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.model.RegisterInfoModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    //登录
    @POST(value = "/user/login")
    Observable<BaseModel<LoginInfoModel>> login(@Query("username") String username,
                                                @Query("password") String password);

    //注册
    @POST(value = "/user/register")
    Observable<BaseModel<RegisterInfoModel>> register(
            @Query(value = "username") final String username,
            @Query(value = "password") final String password,
            @Query(value = "repassword") final String password2);

}
