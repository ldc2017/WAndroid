package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SearchModel;

public interface SearchContract {

    interface V extends IBaseView {
        void get_search_resp(BaseModel<SearchModel> dt);
    }

    interface P {
        void get_search_req(int index, String k);
    }
}
