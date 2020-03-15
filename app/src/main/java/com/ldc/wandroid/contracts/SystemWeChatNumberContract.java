package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberModel;

import java.util.List;

public interface SystemWeChatNumberContract {
    interface V extends IBaseView{
        public void get_wechat_number_resp(BaseModel<List<WeChatNumberModel>> dts);
    }

    interface P{
        public void get_wechat_number_req();
    }
}
