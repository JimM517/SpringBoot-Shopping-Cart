package com.shoppingcart.model;

import java.util.Objects;

public class User {

    //user modal class - using mvc layout


    private int id;
    private String userName;
    private String passwordHash;
    private String role;
    private String name;
    private String address;
    private String city;
    private String stateCode;
    private String zip;


    public User() {}

    private User(int id, String userName, String passwordHash, String role, String name, String address, String city, String stateCode, String zip) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.role = role;
        this.name = name;
        this.address = address;
        this.city = city;
        this.stateCode = stateCode;
        this.zip = zip;
    }

    public int getUserId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(role, user.role) && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(city, user.city) && Objects.equals(stateCode, user.stateCode) && Objects.equals(zip, user.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passwordHash, role, name, address, city, stateCode, zip);
    }
}
