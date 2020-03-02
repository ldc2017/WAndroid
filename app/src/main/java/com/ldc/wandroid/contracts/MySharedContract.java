package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MySharedModel;

public interface MySharedContract {
    interface V extends IBaseView {
        void get_my_shared_resp(BaseModel<MySharedModel> dt);
    }

    interface P {
        void get_my_shared_req(int index);
    }
}
