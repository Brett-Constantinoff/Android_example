package com.example.android_example;

public class UserModel {
    private int id;
    private String name;
    private int age;
    private boolean active;

    public UserModel(int id, String name, int age, boolean is_active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = is_active;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "User ID: " + this.id + " User name: " + this.name + " User Age: " + this.age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean is_active() {
        return active;
    }

    public void setIs_active(boolean is_active) {
        this.active = is_active;
    }
}
