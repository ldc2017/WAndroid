package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemModel;

import java.util.List;

public interface SystemContract {
    interface V extends IBaseView{

        void get_system_resp(BaseModel<List<SystemModel>> dts);

    }
    interface P{
        void get_system_req();
    }
}
