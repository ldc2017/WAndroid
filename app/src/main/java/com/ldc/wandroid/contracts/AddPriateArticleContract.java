package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;

public interface AddPriateArticleContract {
    interface V extends IBaseView {
        void add_private_article_resp(BaseModel<Object> dt);
    }
    interface P{
        void add_private_article_req(final String title,final String link);
    }
}
