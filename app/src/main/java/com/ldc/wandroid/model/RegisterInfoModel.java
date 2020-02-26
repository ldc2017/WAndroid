package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterInfoModel {
    /**
     * admin : false
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 46472
     * nickname : dd2
     * password :
     * publicName : dd2
     * token :
     * type : 0
     * username : dd2
     */

    @SerializedName("admin")
    private boolean nullAdmin;
    @SerializedName("email")
    private String nullEmail;
    @SerializedName("icon")
    private String nullIcon;
    @SerializedName("id")
    private int nullId;
    @SerializedName("nickname")
    private String nullNickname;
    @SerializedName("password")
    private String nullPassword;
    @SerializedName("publicName")
    private String nullPublicName;
    @SerializedName("token")
    private String nullToken;
    @SerializedName("type")
    private int nullType;
    @SerializedName("username")
    private String nullUsername;
    @SerializedName("chapterTops")
    private List<?> nullChapterTops;
    @SerializedName("collectIds")
    private List<?> nullCollectIds;

    public boolean isAdmin() {
        return nullAdmin;
    }

    public void setAdmin(boolean admin) {
        nullAdmin = admin;
    }

    public String getEmail() {
        return nullEmail;
    }

    public void setEmail(String email) {
        nullEmail = email;
    }

    public String getIcon() {
        return nullIcon;
    }

    public void setIcon(String icon) {
        nullIcon = icon;
    }

    public int getId() {
        return nullId;
    }

    public void setId(int id) {
        nullId = id;
    }

    public String getNickname() {
        return nullNickname;
    }

    public void setNickname(String nickname) {
        nullNickname = nickname;
    }

    public String getPassword() {
        return nullPassword;
    }

    public void setPassword(String password) {
        nullPassword = password;
    }

    public String getPublicName() {
        return nullPublicName;
    }

    public void setPublicName(String publicName) {
        nullPublicName = publicName;
    }

    public String getToken() {
        return nullToken;
    }

    public void setToken(String token) {
        nullToken = token;
    }

    public int getType() {
        return nullType;
    }

    public void setType(int type) {
        nullType = type;
    }

    public String getUsername() {
        return nullUsername;
    }

    public void setUsername(String username) {
        nullUsername = username;
    }

    public List<?> getChapterTops() {
        return nullChapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        nullChapterTops = chapterTops;
    }

    public List<?> getCollectIds() {
        return nullCollectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        nullCollectIds = collectIds;
    }
}
