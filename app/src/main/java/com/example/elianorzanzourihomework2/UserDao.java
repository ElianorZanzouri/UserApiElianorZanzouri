package com.example.elianorzanzourihomework2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.elianorzanzourihomework2.user.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<UserData> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserData userData);

    @Query("SELECT * FROM users WHERE value = :value LIMIT 1 ")
    UserData getUserById(String value);
}
