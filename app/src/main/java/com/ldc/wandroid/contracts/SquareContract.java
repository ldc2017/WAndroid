package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SquareArticleModel;

public interface SquareContract {
    interface V extends IBaseView {
        void get_user_article_resp(final BaseModel<SquareArticleModel> data);
        void select_collect_resp(BaseModel<Object> dt);

        void un_select_collect_resp(BaseModel<Object> dt);

    }

    interface P {
        void get_user_article_req(int p);


        void select_collect_req(final String id);

        void un_select_collect_req(final String id);

    }
}
