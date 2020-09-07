package com.raupp.catalog;

import java.util.List;

public class CourseUsers {

    private Course course;
    private List<User> users;


    public CourseUsers() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
