package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;

public interface SystemInfo2Contract {
    interface V extends IBaseView {
        void get_system_info_resp(BaseModel<SystemInfoModel> dts);

        void select_collect_resp(BaseModel<Object> dt);

        void un_select_collect_resp(BaseModel<Object> dt);
    }

    interface P {
        void get_system_info_req(final int index, String cid);

        void select_collect_req(final String id);

        void un_select_collect_req(final String id);
    }
}
