package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MyCollectModel;

public interface MyCollectContract {
    interface V extends IBaseView {
        void get_my_collect_resp(BaseModel<MyCollectModel> dt);
    }

    interface P {
        void get_my_collect_req(int index);
    }
}
