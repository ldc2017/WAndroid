package com.ldc.wandroid.model;

import java.util.List;

public class UserArticleModel {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12421,"link":"https://juejin.im/post/5e6cab506fb9a07cd52bf627","niceDate":"34分钟前","niceShareDate":"34分钟前","origin":"","prefix":"","projectLink":"","publishTime":1584263474000,"selfVisible":0,"shareDate":1584263474000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 让你彻底理解 ViewModel 的实现原理 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12420,"link":"https://juejin.im/post/5e6b3b566fb9a07cae13796f","niceDate":"2小时前","niceShareDate":"2小时前","origin":"","prefix":"","projectLink":"","publishTime":1584258074000,"selfVisible":0,"shareDate":1584258074000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Google提供的高效加载大图方案","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12419,"link":"https://www.jianshu.com/p/3b81107093c4","niceDate":"17小时前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1584201898000,"selfVisible":0,"shareDate":1584201898000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android MVI 详解","type":0,"userId":29303,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12418,"link":"https://juejin.im/post/5e69a4fd51882549003d2f0e","niceDate":"18小时前","niceShareDate":"18小时前","origin":"","prefix":"","projectLink":"","publishTime":1584200144000,"selfVisible":0,"shareDate":1584200144000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"深入探索Android稳定性优化","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12415,"link":"https://www.cnblogs.com/deng-cc/p/6416332.html","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1584179586000,"selfVisible":0,"shareDate":1584179586000,"shareUser":"轻风白宇","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"理解 IntelliJ IDEA 的项目配置和Web部署","type":0,"userId":29185,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12414,"link":"https://github.com/yangkun19921001/Blog","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1584179561000,"selfVisible":0,"shareDate":1584179561000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Android 面试，数据结构和算法 音视频 C/C++ 人工智能 跨平台---学习记录","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12410,"link":"https://juejin.im/post/5e66eca351882549431ffee0","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1584158177000,"selfVisible":0,"shareDate":1584158177000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Android性能优化-你的lottie动画今天跳帧了吗？ ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12409,"link":"https://juejin.im/post/5e6a22336fb9a07c820fc13c","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1584149948000,"selfVisible":0,"shareDate":1584149948000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 10.0中Activity的启动流程","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12408,"link":"https://www.jianshu.com/p/662f1b042529","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1584147405000,"selfVisible":0,"shareDate":1584147405000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android一次完美的跨进程服务共享实践","type":0,"userId":29303,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12397,"link":"https://mp.weixin.qq.com/s?__biz=MzAxOTc0NzExNg==&amp;mid=2665514802&amp;idx=1&amp;sn=b27962488ddd23cda5f67c3f1db12b56&amp;scene=21#wechat_redirect","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584074418000,"selfVisible":0,"shareDate":1584074418000,"shareUser":"残页","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"一个想休息的线程：JVM到底是怎么处理锁的？","type":0,"userId":12467,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12395,"link":"https://www.zhihu.com/question/55075763","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584071218000,"selfVisible":0,"shareDate":1584071218000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Java 的偏向锁是怎么实现的？","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12394,"link":"https://www.jianshu.com/p/b04930d2b85e","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584062485000,"selfVisible":0,"shareDate":1584062485000,"shareUser":"18616720137","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Socket详解","type":0,"userId":36628,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12393,"link":"https://juejin.im/post/5e69cf0c518825496452c6eb","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584059502000,"selfVisible":0,"shareDate":1584059502000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"炫酷！从未见过如此Q弹的Switcher","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12392,"link":"https://juejin.im/post/5e535a4b518825496452b063","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584058495000,"selfVisible":0,"shareDate":1584058495000,"shareUser":"轻风白宇","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Glide都在用的LruCache，你会几分？","type":0,"userId":29185,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12391,"link":"https://www.jianshu.com/p/924046eae137","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584057677000,"selfVisible":0,"shareDate":1584057677000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Androidx 中的 ViewPager 与 ViewPager2","type":0,"userId":29303,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12390,"link":"https://juejin.im/post/5e69966c6fb9a07c9205446b","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584056598000,"selfVisible":0,"shareDate":1584056598000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Now in Android：02 - 欢迎使用 Android Studio 4.0 ！","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12389,"link":"https://blog.csdn.net/weixin_42613755/article/details/104583630","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584017390000,"selfVisible":0,"shareDate":1584017390000,"shareUser":"石头来了","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"React-Navigation 5.X 学习记录（一）------ StackNavigator 篇","type":0,"userId":48213,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12387,"link":"https://juejin.im/post/5e6900c6518825494c75fa0d","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1584010947000,"selfVisible":0,"shareDate":1584010947000,"shareUser":"developerYk","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":":fire::fire::fire:面向 Android 高级工程师的一份面试宝典 (持续更新):fire::fire::fire:","type":0,"userId":898,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12385,"link":"https://juejin.im/post/5a68b8dd51882573505165c5","niceDate":"2020-03-12 15:49","niceShareDate":"2020-03-12 15:49","origin":"","prefix":"","projectLink":"","publishTime":1583999375000,"selfVisible":0,"shareDate":1583999375000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 使用Kotlin takeIf（或takeUnless） ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12344,"link":"https://juejin.im/post/5e68b4146fb9a07ca1372816","niceDate":"2020-03-12 09:06","niceShareDate":"2020-03-12 09:06","origin":"","prefix":"","projectLink":"","publishTime":1583975171000,"selfVisible":0,"shareDate":1583975171000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"volatile引发的一个有趣的测试","type":0,"userId":3559,"visible":0,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 62
     * size : 20
     * total : 1236
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
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
         * id : 12421
         * link : https://juejin.im/post/5e6cab506fb9a07cd52bf627
         * niceDate : 34分钟前
         * niceShareDate : 34分钟前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1584263474000
         * selfVisible : 0
         * shareDate : 1584263474000
         * shareUser : 鸿洋
         * superChapterId : 494
         * superChapterName : 广场Tab
         * tags : []
         * title :  让你彻底理解 ViewModel 的实现原理
         * type : 0
         * userId : 2
         * visible : 0
         * zan : 0
         */

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
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
