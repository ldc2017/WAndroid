package com.ldc.wandroid.contracts;

import com.ldc.wandroid.core.IBaseView;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;

public interface ProjectArticleContract {
    interface V extends IBaseView {
        void get_projects_article_resp(BaseModel<ProjectsArticleModel> dt);
    }

    interface P {
        void get_projects_article_req(int index,String cid);
    }
}
