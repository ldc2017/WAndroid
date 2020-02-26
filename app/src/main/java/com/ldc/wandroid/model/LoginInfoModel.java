package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginInfoModel {
    /**
     * admin : false
     * chapterTops : []
     * collectIds : [10406]
     * email :
     * icon :
     * id : 26308
     * nickname : ldc_2019
     * password :
     * publicName : ldc_2019
     * token :
     * type : 0
     * username : ldc_2019
     */

    @SerializedName("admin")
    private boolean admin;
    @SerializedName("email")
    private String email;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private int id;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("password")
    private String password;
    @SerializedName("publicName")
    private String publicName;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private int type;
    @SerializedName("username")
    private String username;
    @SerializedName("chapterTops")
    private List<String> chapterTops;
    @SerializedName("collectIds")
    private List<Integer> collectIds;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<String> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
