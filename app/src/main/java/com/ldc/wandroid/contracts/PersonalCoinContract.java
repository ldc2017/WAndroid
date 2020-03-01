package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalCoinModel;

public interface PersonalCoinContract {
    interface V extends IBaseView {
        void get_coinCount_resp(BaseModel<PersonalCoinModel> dt);
    }

    interface P {
        void get_coinCount_req(int index);
    }
}
