package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;

import java.util.List;

public interface HomeContract {
    interface V extends IBaseView {
        void get_top_article_resp(BaseModel<List<HomeArticleModel.DatasBean>> data);

        void get_article_resp(BaseModel<HomeArticleModel> data);

        void get_banner_resp(BaseModel<List<BannerModel>> data);


        void select_collect_resp(BaseModel<Object> data);

        void un_select_collect_originId_resp(BaseModel<Object> data);

    }

    interface P {
        void get_top_article_req();

        void get_article_req(int index);

        void get_banner_req();

        void select_collect_req(String id);

        void un_select_collect_originId_req(String id);
    }
}
