package com.ldc.wandroid.db.entitis;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "tb_user", indices = {
        @Index(value = "id"),
        @Index(value = "nickname"),
        @Index(value = "token"),
}, primaryKeys = {"id"})
public class UserEntity {
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "nickname")
    public String nickname;
    @ColumnInfo(name = "publicName")
    public String publicName;
    @ColumnInfo(name = "type")
    public int type;
    @ColumnInfo(name = "token")
    public String token;

    public UserEntity(int id, String email, String username, String nickname, String publicName, int type, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.publicName = publicName;
        this.type = type;
        this.token = token;
    }
}
