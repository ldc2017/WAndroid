package com.ldc.wandroid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ldc.wandroid.db.entitis.UserEntity;

@Database(version = 1, entities = {UserEntity.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
}
