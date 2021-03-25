package com.example.sample_log_app.data.db.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(User user);

    @Query("SELECT * FROM User")
    public List<User> getUserData();

    @Query("DELETE FROM User")
    public void deleteAll();



}
