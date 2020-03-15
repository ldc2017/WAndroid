package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SearchModel;

public interface SearchContract {

    interface V extends IBaseView {
        void get_search_resp(BaseModel<SearchModel> dt);

        void select_collect_resp(BaseModel<Object> data);

        void un_select_collect_originId_resp(BaseModel<Object> data);
    }

    interface P {
        void get_search_req(int index, String k);


        void select_collect_req(String id);

        void un_select_collect_originId_req(String id);
    }
}
