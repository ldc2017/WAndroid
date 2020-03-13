package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PrivateSharedArticleModel;

public interface MyPrivateArticleContract {
    interface V extends IBaseView {
        void get_my_private_article_resp(BaseModel<PrivateSharedArticleModel> dt);
    }

    interface P {
        void get_my_private_article_req(int index);
    }
}
