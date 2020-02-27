package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeArticleModel {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12112,"link":"https://www.jianshu.com/p/df4f4467e5f1","niceDate":"3小时前","niceShareDate":"3小时前","origin":"","prefix":"","projectLink":"","publishTime":1582768766000,"selfVisible":0,"shareDate":1582768766000,"shareUser":"彭旭锐","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android | 这是一份详细的 EventBus 使用教程","type":0,"userId":30587,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12107,"link":"https://juejin.im/post/5e0980fbe51d4558083345fc","niceDate":"17小时前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582719222000,"selfVisible":0,"shareDate":1582719222000,"shareUser":"jingbin","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"ByRecyclerView：只为改变BRVAH加载更多机制/addHeaderView的问题","type":0,"userId":1534,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12081,"link":"https://blog.csdn.net/willway_wang/article/details/104459743","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1582697442000,"selfVisible":0,"shareDate":1582697366000,"shareUser":"willwaywang6","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Java反射学习笔记","type":0,"userId":833,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12080,"link":"https://weilu.blog.csdn.net/article/details/104513170","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582688900000,"selfVisible":0,"shareDate":1582688900000,"shareUser":"唯鹿","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 10 适配攻略","type":0,"userId":2657,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12079,"link":"https://juejin.im/post/5e55d38d518825491753ae39","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582684079000,"selfVisible":0,"shareDate":1582684079000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（七、深入理解ButterKnife源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12078,"link":"https://juejin.im/post/5de5efd96fb9a0715d3cb21d#heading-13","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582684072000,"selfVisible":0,"shareDate":1582684072000,"shareUser":"1063523767","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"新鲜出炉的 MVVM 脚手架 &mdash;&mdash; KtArmor-MVVM","type":0,"userId":6924,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12077,"link":"https://juejin.im/post/5e50be92e51d4526d90d0bd7","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582682390000,"selfVisible":0,"shareDate":1582682390000,"shareUser":"rhyme_lph","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Flutter自制插件之r_album图片或视频保存到相册","type":0,"userId":43844,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12091,"link":"https://mp.weixin.qq.com/s/MNaPWPgDcrIOE8twspURRw","niceDate":"1天前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582646400000,"selfVisible":0,"shareDate":1582718227000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 中实现异步轮询上传文件","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12096,"link":"https://mp.weixin.qq.com/s/0lgYPvwL1B6ohvKoBHC0oQ","niceDate":"1天前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582646400000,"selfVisible":0,"shareDate":1582718335000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"&ldquo;吹上天&rdquo;的Kotlin协程 要不看下实战？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"承香墨影","canEdit":false,"chapterId":411,"chapterName":"承香墨影","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12098,"link":"https://mp.weixin.qq.com/s/FOs7hiUk7_W2GoZsM1Tc_w","niceDate":"1天前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582646400000,"selfVisible":0,"shareDate":1582718386000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"漫画：聊聊线程池中，线程的增长/回收策略","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":471,"chapterName":"10.0（Q）","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12072,"link":"https://juejin.im/post/5e1e76d25188254db475ea24","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582644932000,"selfVisible":0,"shareDate":1582644918000,"shareUser":"鸿洋","superChapterId":453,"superChapterName":"版本适配","tags":[],"title":"Android(Q)10 上的分区外部存储访问","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":232,"chapterName":"入门及知识点","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12064,"link":"https://blog.csdn.net/carson_ho/article/details/104471757","niceDate":"1天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1582644885000,"selfVisible":0,"shareDate":1582555077000,"shareUser":"鸿洋","superChapterId":232,"superChapterName":"Kotlin","tags":[],"title":"巧用Kotlin：内置函数let、also、with、run、apply大大提高你的开发效率！_android,java,kotlin_专注分享 Android开发 干货","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12049,"link":"https://blog.csdn.net/huangliniqng/article/details/104208227","niceDate":"1天前","niceShareDate":"2020-02-23 18:58","origin":"","prefix":"","projectLink":"","publishTime":1582639016000,"selfVisible":0,"shareDate":1582455492000,"shareUser":"Huanglinqing","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"自定义View二篇，如何自定义一个规范的ViewGroup","type":0,"userId":31874,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12069,"link":"https://juejin.im/post/5e4ff123e51d4527255ca2e1","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1582613824000,"selfVisible":0,"shareDate":1582613824000,"shareUser":"jingbin","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"ByRecyclerView：真&middot;万能分割线 (线性/宫格/瀑布流)","type":0,"userId":1534,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12089,"link":"https://mp.weixin.qq.com/s/p6SztffQHubNao_YnkIGlw","niceDate":"2天前","niceShareDate":"18小时前","origin":"","prefix":"","projectLink":"","publishTime":1582560000000,"selfVisible":0,"shareDate":1582718161000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"仿抖音 APP 视频切换和点赞效果","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12090,"link":"https://mp.weixin.qq.com/s/Qp1e0ij3rgE0o6AvAoHycA","niceDate":"2天前","niceShareDate":"18小时前","origin":"","prefix":"","projectLink":"","publishTime":1582560000000,"selfVisible":0,"shareDate":1582718191000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"如何去写一手好 SQL ？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"互联网侦察","canEdit":false,"chapterId":421,"chapterName":"互联网侦察","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12101,"link":"https://mp.weixin.qq.com/s/keEqY8L0ZcsSb-pGSlaVhw","niceDate":"2天前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582560000000,"selfVisible":0,"shareDate":1582718444000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/421/1"}],"title":"Nginx为什么快到根本停不下来？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12105,"link":"https://mp.weixin.qq.com/s/i_pQM90BuA-8qGBJ_f2OqQ","niceDate":"2天前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1582560000000,"selfVisible":0,"shareDate":1582718599000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"用大白话讲解RxJava原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12059,"link":"https://juejin.im/post/5e5330f8e51d4526d43f30ef","niceDate":"2020-02-24 10:20","niceShareDate":"2020-02-24 10:20","origin":"","prefix":"","projectLink":"","publishTime":1582510821000,"selfVisible":0,"shareDate":1582510821000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（六、深入理解Leakcanary源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12058,"link":"https://www.jianshu.com/p/4f6dc8ebc034","niceDate":"2020-02-24 09:59","niceShareDate":"2020-02-24 09:59","origin":"","prefix":"","projectLink":"","publishTime":1582509582000,"selfVisible":0,"shareDate":1582509582000,"shareUser":"吊儿郎当","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"OkHttp原理解析1 (框架流程篇)","type":0,"userId":2554,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 402
     * size : 20
     * total : 8028
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
         * chapterId : 502
         * chapterName : 自助
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : true
         * id : 12112
         * link : https://www.jianshu.com/p/df4f4467e5f1
         * niceDate : 3小时前
         * niceShareDate : 3小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1582768766000
         * selfVisible : 0
         * shareDate : 1582768766000
         * shareUser : 彭旭锐
         * superChapterId : 494
         * superChapterName : 广场Tab
         * tags : []
         * title : Android | 这是一份详细的 EventBus 使用教程
         * type : 0
         * userId : 30587
         * visible : 1
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
