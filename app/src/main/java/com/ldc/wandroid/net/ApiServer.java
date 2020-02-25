package com.ldc.wandroid.net;

import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    @POST(value = "/user/login")
    Observable<BaseModel<LoginInfoModel>> login(@Query("username") String username,
                                                @Query("password") String password);
}
