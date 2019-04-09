package com.example.myapplication;

public class Chat {

    private String user, message,date;

    Chat(String user, String message, String date) {
        this.user = user;
        this.message = message;
        this.date = date;
    }

    String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
