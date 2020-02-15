package com.htw.structure;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserInfo extends RealmObject {
    private String id = null;
    @PrimaryKey
    private String name = null;//姓名
    private String gender = null;//性别
    private int age = 0;//年龄

    public UserInfo() {

    }

    private UserInfo(UserInfo userInfo) {
        this.id = userInfo.id;
        this.name = userInfo.name;
        this.gender = userInfo.gender;
        this.age = userInfo.age;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static class Builder {
        private UserInfo targer = null;

        public Builder() {
            targer = new UserInfo();
        }

        public Builder id(String id) {
            targer.id = id;
            return this;
        }

        public Builder name(String name) {
            targer.name = name;
            return this;
        }

        public Builder gender(String gender) {
            targer.gender = gender;
            return this;
        }

        public Builder age(int age) {
            targer.age = age;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(targer);
        }
    }
}
