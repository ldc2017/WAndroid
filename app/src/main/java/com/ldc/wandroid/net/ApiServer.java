package com.ldc.wandroid.net;

import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.model.NetNavigationModel;
import com.ldc.wandroid.model.RegisterInfoModel;
import com.ldc.wandroid.model.SystemInfoModel;
import com.ldc.wandroid.model.SystemModel;
import com.ldc.wandroid.model.TopArticleModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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


    // 获取置顶文章
    @GET(value = "/article/top/json")
    Observable<BaseModel<List<TopArticleModel>>> getTopArticle();


    //banner
    @GET(value = "/banner/json")
    Observable<BaseModel<List<BannerModel>>> get_banner();


    //首页文章
    @GET(value = "/article/list/{index}/json")
    Observable<BaseModel<HomeArticleModel>> get_home_article(@Path(value = "index") int index);


    //体系
    @GET(value = "/tree/json")
    Observable<BaseModel<List<SystemModel>>> get_system();

    //导航
    @GET(value = "/friend/json")
    Observable<BaseModel<List<NetNavigationModel>>> get_navigation();


    //体系下文章
    @GET(value = "/article/list/{index}/json")
    Observable<BaseModel<SystemInfoModel>> get_system_info(
            @Path(value = "index") int index,
            @Query(value = "cid") String cid

    );

}
