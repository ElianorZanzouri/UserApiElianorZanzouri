package com.example.elianorzanzourihomework2;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "users",
        indices = {@Index(value={"value"},unique=true)})
public class UserData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="first_name")
    public String firstName;


    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "value")
    public String value;
    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "picture")
    public String picture;


    @ColumnInfo(name="city")
    public String city;

}
