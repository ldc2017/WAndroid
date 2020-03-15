package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberHistoryModel;

public interface WeChatNumberHistoryContract {
    interface V extends IBaseView {

        void get_wechat_number_hiostory_resp(BaseModel<WeChatNumberHistoryModel> dt);

        void select_collect_resp(BaseModel<Object> data);

        void un_select_collect_originId_resp(BaseModel<Object> data);
    }

    interface P {
        void get_wechat_number_hiostory_req(String wechar_id, int p);


        void select_collect_req(String id);

        void un_select_collect_originId_req(String id);
    }
}
