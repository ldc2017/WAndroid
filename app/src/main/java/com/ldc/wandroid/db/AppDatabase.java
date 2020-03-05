package com.ldc.wandroid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ldc.wandroid.db.dao.IntegralDao;
import com.ldc.wandroid.db.dao.UserDao;
import com.ldc.wandroid.db.entitis.IntegralEntity;
import com.ldc.wandroid.db.entitis.UserEntity;

@Database(version = 2, entities = {IntegralEntity.class, UserEntity.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract IntegralDao integralDao();

    public abstract UserDao userDao();
}
