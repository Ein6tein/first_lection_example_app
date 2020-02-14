package com.igor.app.model;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Employee {

    private int mId;
    private String mName;
    private int mAge;
    private int mSalary;
    private String mImage;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getSalary() {
        return mSalary;
    }

    public void setSalary(int salary) {
        mSalary = salary;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}