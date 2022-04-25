package com.example.studentapp;

public class student {
    String id;
    String name;
    String city;
    String dob;

    public student() {
    }

    public student(String id, String name, String city, String dob) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.dob = dob;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "\n student: " +
                "id= " + id + '\n' +
                "name= " + name + '\n' +
                "city= " + city + '\n' +
                "dob= " + dob + '\n';
    }
}
