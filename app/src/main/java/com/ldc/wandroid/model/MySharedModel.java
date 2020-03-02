package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MySharedModel {

    /**
     * coinInfo : {"coinCount":756,"level":8,"rank":656,"userId":26308,"username":"l**_2019"}
     * shareArticles : {"curPage":1,"datas":[{"apkLink":"","audit":0,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12160,"link":"https://github.com/yanzhenjie/AndPermission","niceDate":"刚刚","niceShareDate":"刚刚","origin":"","prefix":"","projectLink":"","publishTime":1583141182000,"selfVisible":0,"shareDate":1583141182000,"shareUser":"ldc_2019","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"好用的权限框架&middot;&middot;&middot;&middot;（test）","type":0,"userId":26308,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":1}
     */

    @SerializedName("coinInfo")
    private CoinInfoBean coinInfo;
    @SerializedName("shareArticles")
    private ShareArticlesBean shareArticles;

    public CoinInfoBean getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoBean coinInfo) {
        this.coinInfo = coinInfo;
    }

    public ShareArticlesBean getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(ShareArticlesBean shareArticles) {
        this.shareArticles = shareArticles;
    }

    public static class CoinInfoBean {
        /**
         * coinCount : 756
         * level : 8
         * rank : 656
         * userId : 26308
         * username : l**_2019
         */

        @SerializedName("coinCount")
        private int coinCount;
        @SerializedName("level")
        private int level;
        @SerializedName("rank")
        private int rank;
        @SerializedName("userId")
        private int userId;
        @SerializedName("username")
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ShareArticlesBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":0,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12160,"link":"https://github.com/yanzhenjie/AndPermission","niceDate":"刚刚","niceShareDate":"刚刚","origin":"","prefix":"","projectLink":"","publishTime":1583141182000,"selfVisible":0,"shareDate":1583141182000,"shareUser":"ldc_2019","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"好用的权限框架&middot;&middot;&middot;&middot;（test）","type":0,"userId":26308,"visible":0,"zan":0}]
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
             * apkLink :
             * audit : 0
             * author :
             * canEdit : false
             * chapterId : 494
             * chapterName : 广场
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : true
             * id : 12160
             * link : https://github.com/yanzhenjie/AndPermission
             * niceDate : 刚刚
             * niceShareDate : 刚刚
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1583141182000
             * selfVisible : 0
             * shareDate : 1583141182000
             * shareUser : ldc_2019
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : 好用的权限框架&middot;&middot;&middot;&middot;（test）
             * type : 0
             * userId : 26308
             * visible : 0
             * zan : 0
             */

            @SerializedName("apkLink")
            private String apkLink;
            @SerializedName("audit")
            private int audit;
            @SerializedName("author")
            private String author;
            @SerializedName("canEdit")
            private boolean canEdit;
            @SerializedName("chapterId")
            private int chapterId;
            @SerializedName("chapterName")
            private String chapterName;
            @SerializedName("collect")
            private boolean collect;
            @SerializedName("courseId")
            private int courseId;
            @SerializedName("desc")
            private String desc;
            @SerializedName("descMd")
            private String descMd;
            @SerializedName("envelopePic")
            private String envelopePic;
            @SerializedName("fresh")
            private boolean fresh;
            @SerializedName("id")
            private int id;
            @SerializedName("link")
            private String link;
            @SerializedName("niceDate")
            private String niceDate;
            @SerializedName("niceShareDate")
            private String niceShareDate;
            @SerializedName("origin")
            private String origin;
            @SerializedName("prefix")
            private String prefix;
            @SerializedName("projectLink")
            private String projectLink;
            @SerializedName("publishTime")
            private long publishTime;
            @SerializedName("selfVisible")
            private int selfVisible;
            @SerializedName("shareDate")
            private long shareDate;
            @SerializedName("shareUser")
            private String shareUser;
            @SerializedName("superChapterId")
            private int superChapterId;
            @SerializedName("superChapterName")
            private String superChapterName;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private int type;
            @SerializedName("userId")
            private int userId;
            @SerializedName("visible")
            private int visible;
            @SerializedName("zan")
            private int zan;
            @SerializedName("tags")
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
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

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
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

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
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

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
