package com.example.emotion_firebase.entity;

public class User {
    private String keyID;
    private String email;
    private String password;
    private int smile;
    private int angry;
    private int bored;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, int smile, int angry, int bored) {
        this.email = email;
        this.password = password;
        this.smile = smile;
        this.angry = angry;
        this.bored = bored;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
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

    public int getSmile() {
        return smile;
    }

    public void setSmile(int smile) {
        this.smile = smile;
    }

    public int getAngry() {
        return angry;
    }

    public void setAngry(int angry) {
        this.angry = angry;
    }

    public int getBored() {
        return bored;
    }

    public void setBored(int bored) {
        this.bored = bored;
    }
}
