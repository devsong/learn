package com.gzs.learn.patterdesign.create.builder;

import lombok.Data;

@Data
// @Builder
public class User {
    private Integer id;
    private String name;
    private Integer gender;
    private String phone;
    private String email;
    private String address;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

}


class UserBuilder {
    private Integer id;
    private String name;
    private Integer gender;
    private String phone;
    private String email;
    private String address;

    public UserBuilder() {

    }

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setGender(this.gender);
        user.setPhone(this.phone);
        user.setEmail(this.email);
        user.setAddress(this.address);
        return user;
    }

}
