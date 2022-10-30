package com.example.demo.sample.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 2hu0
 */
@TableName("user")
public class User {

    @TableId("user_id")
    private Integer id;

    @TableField("user_name")
    private String username;

    @TableField("user_password")
    private String password;


    /**
     * 已完成练习
     */


    @TableField("user_completed")
    private int completedExercises;


    /**
     * 错题数
     */

    @TableField("user_error_exercises")
    private int errorExercises;

    @TableField("user_last_login_time")
    private Date recentTime;

    @TableField("user_create_time")
    private Date createTime;

    public User() {
        this.id = id;
        this.username = username;
        this.password = password;
        this.completedExercises = completedExercises;
        this.errorExercises = errorExercises;
        this.recentTime = recentTime;
    }

    public User(int id, String username, String password, int completedExercises, int errorExercises, Date recentTime, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.completedExercises = completedExercises;
        this.errorExercises = errorExercises;
        this.recentTime = recentTime;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Date date) {
        this.username = username;
        this.password = password;
        this.recentTime = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCompletedExercises() {
        return completedExercises;
    }

    public void setCompletedExercises(int completedExercises) {
        this.completedExercises = completedExercises;
    }

    public int getErrorExercises() {
        return errorExercises;
    }

    public void setErrorExercises(int errorExercises) {
        this.errorExercises = errorExercises;
    }

    public Date getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Date recentTime) {
        this.recentTime = recentTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", completedExercises=" + completedExercises +
                ", errorExercises=" + errorExercises +
                ", recentTime=" + recentTime +
                '}';
    }
}
