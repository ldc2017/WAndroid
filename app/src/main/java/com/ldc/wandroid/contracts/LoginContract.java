package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;

public interface LoginContract {
    interface V extends IBaseView {
        void login_resp(final BaseModel<LoginInfoModel> data);
    }

    interface P {
        void login_req(final String username, final String password);
    }
}
