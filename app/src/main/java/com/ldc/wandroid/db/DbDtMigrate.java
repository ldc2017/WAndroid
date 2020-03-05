package com.ldc.wandroid.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DbDtMigrate {


    //新添加一个 user_tb 表
    public static final Migration v_1_2_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            final String sql = "" +
                    "create table 'user_tb'(" +
                    "'id' int," +
                    "'email' text," +
                    "'username' text," +
                    "'nikename' text," +
                    "'publicname' text," +
                    "'type' int," +
                    "'token' text," +
                    "primary key('id')" +
                    ");" +
                    "" +
                    "";
            database.execSQL(sql);

        }
    };
}
