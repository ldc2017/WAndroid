package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.NavigationModel;

import java.util.List;

public interface SystemNavigationContract {
    interface V extends IBaseView {
        void get_navigation_resp(BaseModel<List<NavigationModel>> dts);
    }

    interface P {
        void get_navigation_req();
    }
}
