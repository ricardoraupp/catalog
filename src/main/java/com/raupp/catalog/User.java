package com.raupp.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.math.BigInteger;


public class User {
    private BigInteger userId;
    private BigInteger courseId;
    private String username;

    public User() {
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
