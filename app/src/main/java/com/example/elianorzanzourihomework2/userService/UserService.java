package com.example.elianorzanzourihomework2.userService;

import com.example.elianorzanzourihomework2.user.Result;
import com.example.elianorzanzourihomework2.user.User;
import com.example.elianorzanzourihomework2.user.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/api")
    public Call<User> getUsers(
            @Query("limit") Integer limit,
            @Query("skip") Integer skip,
            @Query("select") String select

    );

}
