package com.deepak.fts.model;

/**
 * Created by Deepak Goyal on 24/1/17.
 * Author: Deepak Goyal
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
