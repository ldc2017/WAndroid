package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.RegisterInfoModel;

public interface RegisterContract {
    interface V extends IBaseView{
        void register_resp(BaseModel<RegisterInfoModel> data);
    }
    interface P{
        void register_req(final String username,final String pwd,final String pwd2);
    }
}
