package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

public class IntegralModel {
    /**
     * coinCount : 735
     * level : 8
     * rank : 679
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
