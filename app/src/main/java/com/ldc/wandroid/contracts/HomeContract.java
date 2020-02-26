package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.TopArticleModel;

import java.util.List;

public interface HomeContract {
    interface V extends IBaseView {
        void get_top_article_resp(BaseModel<List<TopArticleModel>> data);
    }

    interface P {
        void get_top_article_req();
    }
}
