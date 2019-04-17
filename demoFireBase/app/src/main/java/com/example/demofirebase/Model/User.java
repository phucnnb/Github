package com.example.demofirebase.Model;

public class User {

    String email;
    String linkImage;

    public User() {
    }



    public User(String email, String linkImage) {
        this.email = email;
        this.linkImage = linkImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
