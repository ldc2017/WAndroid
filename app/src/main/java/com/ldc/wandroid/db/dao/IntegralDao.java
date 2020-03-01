package com.ldc.wandroid.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ldc.wandroid.db.entitis.IntegralEntity;

@Dao
public interface IntegralDao {


    //获取用户积分
    @Query(value = "select * from tb_integral where userId == :user_id")
    IntegralEntity find_by_user_id(String user_id);


    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IntegralEntity... ets);

}
