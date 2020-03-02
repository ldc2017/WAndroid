package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCollectModel {
    /**
     * curPage : 1
     * datas : [{"author":"iceCola7","chapterId":402,"chapterName":"跨平台应用","courseId":13,"desc":"项目基于 Google 推出的一款用于创建跨平台、高性能的移动应用框架&mdash;&mdash;Flutter ，采用 Dart 语言编写，打造一款优秀的 WanAndroid 客户端，项目会持续迭代更新。","envelopePic":"https://www.wanandroid.com/blogimgs/1d2ee0cc-2cef-45fb-9e13-c073bc561e0d.png","id":103092,"link":"http://www.wanandroid.com/blog/show/2705","niceDate":"2019-11-30 22:11","origin":"","originId":10406,"publishTime":1575123098000,"title":"Flutter 版 WanAndroid 客户端","userId":26308,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 1
     */

    @SerializedName("curPage")
    private int curPage;
    @SerializedName("offset")
    private int offset;
    @SerializedName("over")
    private boolean over;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("size")
    private int size;
    @SerializedName("total")
    private int total;
    @SerializedName("datas")
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : iceCola7
         * chapterId : 402
         * chapterName : 跨平台应用
         * courseId : 13
         * desc : 项目基于 Google 推出的一款用于创建跨平台、高性能的移动应用框架&mdash;&mdash;Flutter ，采用 Dart 语言编写，打造一款优秀的 WanAndroid 客户端，项目会持续迭代更新。
         * envelopePic : https://www.wanandroid.com/blogimgs/1d2ee0cc-2cef-45fb-9e13-c073bc561e0d.png
         * id : 103092
         * link : http://www.wanandroid.com/blog/show/2705
         * niceDate : 2019-11-30 22:11
         * origin :
         * originId : 10406
         * publishTime : 1575123098000
         * title : Flutter 版 WanAndroid 客户端
         * userId : 26308
         * visible : 0
         * zan : 0
         */

        @SerializedName("author")
        private String author;
        @SerializedName("chapterId")
        private int chapterId;
        @SerializedName("chapterName")
        private String chapterName;
        @SerializedName("courseId")
        private int courseId;
        @SerializedName("desc")
        private String desc;
        @SerializedName("envelopePic")
        private String envelopePic;
        @SerializedName("id")
        private int id;
        @SerializedName("link")
        private String link;
        @SerializedName("niceDate")
        private String niceDate;
        @SerializedName("origin")
        private String origin;
        @SerializedName("originId")
        private int originId;
        @SerializedName("publishTime")
        private long publishTime;
        @SerializedName("title")
        private String title;
        @SerializedName("userId")
        private int userId;
        @SerializedName("visible")
        private int visible;
        @SerializedName("zan")
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
