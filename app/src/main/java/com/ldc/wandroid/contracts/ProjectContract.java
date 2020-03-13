package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.model.ProjectsModel;

import java.util.List;

public interface ProjectContract {
    interface V extends IBaseView {
        void get_project_resp(BaseModel<List<ProjectsModel>> dts);
        void get_projects_article_resp(BaseModel<ProjectsArticleModel> dt);


        void select_collect_resp(BaseModel<Object> dt);

        void un_select_collect_resp(BaseModel<Object> dt);
    }

    interface P {
        void get_project_req();
        void get_projects_article_req(int index,String cid);


        void select_collect_req(final String id);

        void un_select_collect_req(final String id);
    }
}
