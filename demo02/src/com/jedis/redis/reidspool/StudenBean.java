package com.jedis.redis.reidspool;

public class StudenBean {
    private Integer id;
    private String name;
    private String gender;

    public StudenBean() {
    }



    @Override
    public String toString() {
        return "StudenBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
