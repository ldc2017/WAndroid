package com.ldc.wandroid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ldc.wandroid.db.dao.IntegralDao;
import com.ldc.wandroid.db.entitis.IntegralEntity;

@Database(version = 1, entities = {IntegralEntity.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract IntegralDao integralDao();
}
