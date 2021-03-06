package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

public class BannerModel {
    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://www.wanandroid.com/blogimgs/fceb1aac-68be-44b9-bcbb-8512e333acc6.jpeg
     * isVisible : 1
     * order : 0
     * title : 你开发的app为什么会被用户卸载？
     * type : 0
     * url : https://mp.weixin.qq.com/s/5AvHcbVySjAoKKkIy0ZjJQ
     */

    @SerializedName("desc")
    private String desc;
    @SerializedName("id")
    private int id;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("isVisible")
    private int isVisible;
    @SerializedName("order")
    private int order;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("url")
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
