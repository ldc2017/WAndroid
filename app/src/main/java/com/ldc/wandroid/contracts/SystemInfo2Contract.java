package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;

public interface SystemInfo2Contract {
    interface V extends IBaseView {
        void get_system_info_resp(BaseModel<SystemInfoModel> dts);
    }

    interface P {
        void get_system_info_req(final int index, String cid);
    }
}
