package com.elon.version.three.entity;

import java.io.Serializable;

/**
 * 2017/5/12 14:58.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class User implements Serializable {

    private String name;

    private int age;

    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
