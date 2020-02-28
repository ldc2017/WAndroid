package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

public class NavigationModel {
    /**
     * icon :
     * id : 17
     * link : http://www.wanandroid.com/article/list/0?cid=176
     * name : 国内大牛博客集合
     * order : 1
     * visible : 1
     */

    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private int id;
    @SerializedName("link")
    private String link;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;
    @SerializedName("visible")
    private int visible;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
