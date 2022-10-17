package com.example.Login;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("status") String status;
    @SerializedName("statusMessage") String statusMessage;

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
