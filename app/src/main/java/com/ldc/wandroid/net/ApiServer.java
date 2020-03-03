package com.ldc.wandroid.net;

import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
import com.ldc.wandroid.model.IntegralModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.model.MyCollectModel;
import com.ldc.wandroid.model.MySharedModel;
import com.ldc.wandroid.model.NetNavigationModel;
import com.ldc.wandroid.model.PersonalCoinModel;
import com.ldc.wandroid.model.PersonalRankModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.model.RegisterInfoModel;
import com.ldc.wandroid.model.SearchModel;
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

    //项目
    @GET(value = "/project/tree/json")
    Observable<BaseModel<List<ProjectsModel>>> get_projects();

    //项目列表
    @GET(value = "/project/list/{index}/json")
    Observable<BaseModel<ProjectsArticleModel>> get_projects_article(
            @Path(value = "index") int index,
            @Query(value = "cid") String cid
    );


    //获取积分
    @GET(value = "/lg/coin/userinfo/json")
    Observable<BaseModel<IntegralModel>> get_integral();


    //获取个人积分获取列表
    @GET(value = "/lg/coin/list/{index}/json")
    Observable<BaseModel<PersonalCoinModel>> get_coinCount(@Path(value = "index") int index);

    //获取积分排名
    @GET(value = "/coin/rank/{index}/json")
    Observable<BaseModel<PersonalRankModel>> get_coin_rank(
            @Path(value = "index") int index
    );

    //我的分享
    @GET(value = "/user/lg/private_articles/{index}/json")
    Observable<BaseModel<MySharedModel>> get_my_shared(
            @Path(value = "index") int index
    );

    //我的收藏
    @GET(value = "/lg/collect/list/{index}/json")
    Observable<BaseModel<MyCollectModel>> get_my_collect(@Path(value = "index") int index);

    //搜索
    @POST(value = "/article/query/{index}/json")
    Observable<BaseModel<SearchModel>> get_search(
            @Path(value = "index") int index,
            @Query(value = "k") String k
    );


    // 推出
    @GET(value = "/user/logout/json")
    Observable<BaseModel<Object>> logout();


}
