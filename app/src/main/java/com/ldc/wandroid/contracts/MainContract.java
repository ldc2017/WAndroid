package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;

public interface MainContract {
    interface V extends IBaseView {


        void get_logout_resp(BaseModel<Object> dt);
    }

    interface P {

        void get_logout_req();
    }
}
