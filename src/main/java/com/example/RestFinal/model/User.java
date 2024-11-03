package com.example.RestFinal.model;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return this.age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String toString() {
        return "User{id=" + this.id + ", name='" + this.name + "', lastName='" + this.lastName + "', age=" + this.age + "}";
    }
}