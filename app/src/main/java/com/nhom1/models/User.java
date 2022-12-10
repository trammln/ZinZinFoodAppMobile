package com.nhom1.models;

public class User {
    int userId;
    String userName;
    String userType;
    String DOB;
    String phoneNumb;
    String email;
    String password;
    String SEX;

    public User(int userId, String userName, String userType, String DOB, String phoneNumb, String email, String password, String SEX) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.DOB = DOB;
        this.phoneNumb = phoneNumb;
        this.email = email;
        this.password = password;
        this.SEX = SEX;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }
}
