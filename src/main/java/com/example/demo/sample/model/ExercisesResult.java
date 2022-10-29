package com.example.demo.sample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 2hu0
 */
public class ExercisesResult {

    private User user;
    /**
     * 答案列表
     */
    private List<String> resList =  new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getResList() {
        return resList;
    }

    public void setResList(List<String> resList) {
        this.resList = resList;
    }
}
