package com.ldc.wandroid.db.entitis;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "tb_integral", primaryKeys = {"id"},
        indices = {
                @Index({"id", "coinCount", "level", "rank", "userId", "username"})
        })
public class IntegralEntity {

    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "coinCount")
    private int coinCount;
    @ColumnInfo(name = "level")
    private int level;
    @ColumnInfo(name = "rank")
    private int rank;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "username")
    private String username;

    public IntegralEntity(long id, int coinCount, int level, int rank, int userId, String username) {
        this.id = id;
        this.coinCount = coinCount;
        this.level = level;
        this.rank = rank;
        this.userId = userId;
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
