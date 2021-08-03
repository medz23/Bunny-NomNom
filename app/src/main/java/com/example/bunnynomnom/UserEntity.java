package com.example.bunnynomnom;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "password2")
    String password2;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "bName")
    String bName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getPassword2() {
        return password2;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getbName() {
        return bName;
    }
}
