package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
import com.ldc.wandroid.model.TopArticleModel;

import java.util.List;

public interface HomeContract {
    interface V extends IBaseView {
        void get_top_article_resp(BaseModel<List<TopArticleModel>> data);

        void get_article_resp(BaseModel<HomeArticleModel> data);

        void get_banner_resp(BaseModel<List<BannerModel>> data);

    }

    interface P {
        void get_top_article_req();

        void get_article_req(int index);

        void get_banner_req();
    }
}
