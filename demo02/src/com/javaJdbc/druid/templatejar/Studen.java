package com.javaJdbc.druid.templatejar;

import java.io.Serializable;

public class Studen implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Studen{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", gender=" + gender + '}';
    }
}
