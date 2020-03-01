package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.IntegralModel;

public interface MainContract {
    interface V extends IBaseView {
        void get_integral_resp(BaseModel<IntegralModel> dt);
    }

    interface P {
        void get_integral_req();
    }
}
