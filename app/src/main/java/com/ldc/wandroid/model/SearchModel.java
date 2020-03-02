package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {
    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12158,"link":"https://juejin.im/entry/5e5c653a6fb9a07c85143108","niceDate":"4小时前","niceShareDate":"4小时前","origin":"","prefix":"","projectLink":"","publishTime":1583129250000,"selfVisible":0,"shareDate":1583129250000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"抖音BoostMultiDex优化实践：<em class='highlight'>Android<\/em>低版本上APP首次启动时间减少80%（一）","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12157,"link":"https://juejin.im/post/5e5b67336fb9a07c96459beb","niceDate":"7小时前","niceShareDate":"7小时前","origin":"","prefix":"","projectLink":"","publishTime":1583119939000,"selfVisible":0,"shareDate":1583119939000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"谈<em class='highlight'>Android<\/em>系统启动流程","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12156,"link":"https://juejin.im/post/5e5b50eb6fb9a07cae136773","niceDate":"7小时前","niceShareDate":"7小时前","origin":"","prefix":"","projectLink":"","publishTime":1583117309000,"selfVisible":0,"shareDate":1583117309000,"shareUser":"于慢慢家的吴蜀黍","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"2020年中高级<em class='highlight'>Android<\/em>大厂面试秘籍，为你保驾护航金三银四，直通大厂","type":0,"userId":1260,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12154,"link":"https://juejin.im/post/5e5b50eb6fb9a07cae136773","niceDate":"9小时前","niceShareDate":"9小时前","origin":"","prefix":"","projectLink":"","publishTime":1583112725000,"selfVisible":0,"shareDate":1583112725000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【建议收藏】2020年中高级<em class='highlight'>Android<\/em>大厂面试秘籍，为你保驾护航金三银四，直通大厂","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12153,"link":"https://www.jianshu.com/p/5c2eed7668e0","niceDate":"10小时前","niceShareDate":"10小时前","origin":"","prefix":"","projectLink":"","publishTime":1583107661000,"selfVisible":0,"shareDate":1583107661000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em> 沉浸式状态栏","type":0,"userId":29303,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12150,"link":"https://blog.csdn.net/qq_39424143/article/details/90045983","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1583061664000,"selfVisible":0,"shareDate":1583061664000,"shareUser":"wjxbless","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em> Junit单元测试 基于Uiautomator UI测试","type":0,"userId":29633,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12145,"link":"https://juejin.im/post/5e59d0eef265da57315b0b0e","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1583028438000,"selfVisible":0,"shareDate":1583028438000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em>开发框架Collection-kotlin","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12144,"link":"https://www.jianshu.com/p/0a9e2dbc05ee","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582992857000,"selfVisible":0,"shareDate":1582992857000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【中文翻译】<em class='highlight'>Android<\/em>单线程事件驱动模型起源 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12140,"link":"https://juejin.im/post/5e563ec1e51d4526ed66b35e#heading-23","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1582971285000,"selfVisible":0,"shareDate":1582971285000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" <em class='highlight'>Android<\/em> NDK入门：C++ 基础知识 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12136,"link":"https://juejin.im/post/5e56716f6fb9a07ce01a2a90","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1582944809000,"selfVisible":0,"shareDate":1582944809000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em>性能优化在大型App的实践经验","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12130,"link":"https://blog.csdn.net/gemmem/article/details/8920039","niceDate":"2020-02-28 18:03","niceShareDate":"2020-02-28 18:03","origin":"","prefix":"","projectLink":"","publishTime":1582884236000,"selfVisible":0,"shareDate":1582884236000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em>进程的内存管理分析","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12128,"link":"https://tech.meituan.com/2019/11/14/crash-oom-probe-practice.html","niceDate":"2020-02-28 17:40","niceShareDate":"2020-02-28 17:40","origin":"","prefix":"","projectLink":"","publishTime":1582882823000,"selfVisible":0,"shareDate":1582882823000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Probe：<em class='highlight'>Android<\/em>线上OOM问题定位组件 - 美团技术团队","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12127,"link":"https://www.androidperformance.com/2015/07/20/Android-Performance-Memory-onTrimMemory/","niceDate":"2020-02-28 16:59","niceShareDate":"2020-02-28 16:59","origin":"","prefix":"","projectLink":"","publishTime":1582880389000,"selfVisible":0,"shareDate":1582880389000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em> 代码内存优化建议-OnTrimMemory 优化 &middot; <em class='highlight'>Android<\/em> Performance","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12122,"link":"https://juejin.im/post/5e58779f518825493f6ce7eb","niceDate":"2020-02-28 10:26","niceShareDate":"2020-02-28 10:26","origin":"","prefix":"","projectLink":"","publishTime":1582856797000,"selfVisible":0,"shareDate":1582856797000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em>主流三方库源码分析（八、深入理解Dagger2源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12120,"link":"https://www.jianshu.com/p/45c8a6cc2904","niceDate":"2020-02-28 08:02","niceShareDate":"2020-02-28 08:02","origin":"","prefix":"","projectLink":"","publishTime":1582848136000,"selfVisible":0,"shareDate":1582848136000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em>面试必问：事件分发机制你肯定得懂！","type":0,"userId":29303,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12112,"link":"https://www.jianshu.com/p/df4f4467e5f1","niceDate":"2020-02-27 09:59","niceShareDate":"2020-02-27 09:59","origin":"","prefix":"","projectLink":"","publishTime":1582768766000,"selfVisible":0,"shareDate":1582768766000,"shareUser":"彭旭锐","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em> | 这是一份详细的 EventBus 使用教程","type":0,"userId":30587,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12110,"link":"https://juejin.im/post/5e564367e51d4526e807f0e4","niceDate":"2020-02-27 08:42","niceShareDate":"2020-02-27 08:42","origin":"","prefix":"","projectLink":"","publishTime":1582764136000,"selfVisible":0,"shareDate":1582764136000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"<em class='highlight'>Android<\/em> 10 适配攻略","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":10916,"link":"https://wanandroid.com/blog/show/2701","niceDate":"2020-02-26 22:22","niceShareDate":"2019-12-17 13:15","origin":"","prefix":"","projectLink":"","publishTime":1582726920000,"selfVisible":0,"shareDate":1576559725000,"shareUser":"18818486692","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"玩 <em class='highlight'>Android<\/em> 交流星球","type":1,"userId":22014,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12108,"link":"https://juejin.im/post/5e55dfb76fb9a07cb345e7cc","niceDate":"2020-02-26 22:10","niceShareDate":"2020-02-26 22:10","origin":"","prefix":"","projectLink":"","publishTime":1582726214000,"selfVisible":0,"shareDate":1582726214000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 一篇文章让你弄懂<em class='highlight'>Android<\/em> Debug调试 ","type":0,"userId":2,"visible":0,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 197
     * size : 20
     * total : 3929
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
         * audit : 1
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
         * id : 12158
         * link : https://juejin.im/entry/5e5c653a6fb9a07c85143108
         * niceDate : 4小时前
         * niceShareDate : 4小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1583129250000
         * selfVisible : 0
         * shareDate : 1583129250000
         * shareUser : goweii
         * superChapterId : 494
         * superChapterName : 广场Tab
         * tags : []
         * title : 抖音BoostMultiDex优化实践：<em class='highlight'>Android</em>低版本上APP首次启动时间减少80%（一）
         * type : 0
         * userId : 20382
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
