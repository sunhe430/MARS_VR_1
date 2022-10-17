package com.example.Login;

import android.app.Application;

public class User extends Application {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
