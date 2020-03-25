package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsModel;

import java.util.List;

public interface ProjectTabContract {
    interface V extends IBaseView {
        void get_project_resp(BaseModel<List<ProjectsModel>> dts);
    }

    interface P {
        void get_project_req();
    }
}
