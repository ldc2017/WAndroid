package com.ldc.wandroid.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ldc.wandroid.db.entitis.UserEntity;

@Dao
public interface UserDao {


    //获取用户积分
    @Query(value = "select * from tb_user where id == :user_id")
    UserEntity find_by_user_id(String user_id);


    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity... ets);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(UserEntity... ets);

}
