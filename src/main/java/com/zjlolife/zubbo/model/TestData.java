package com.zjlolife.zubbo.model;

import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
public class TestData implements Serializable {

    private static final long serialVersionUID = -3218833163423448461L;

    private long id;

    private String userName;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

