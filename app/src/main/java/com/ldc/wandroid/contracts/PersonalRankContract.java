package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalRankModel;

public interface PersonalRankContract {
    interface V extends IBaseView {
        void get_coin_rank_resp(BaseModel<PersonalRankModel> dt);
    }

    interface P {
        void get_coin_rank_req(int index);
    }
}
