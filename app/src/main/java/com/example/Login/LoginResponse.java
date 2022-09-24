package com.example.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("name") String name;
    @SerializedName("status") String status;
    @SerializedName("statusMessage") String statusMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
