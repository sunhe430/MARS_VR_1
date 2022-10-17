package com.example.Mypage;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {
    @SerializedName("name") String name;
    @SerializedName("pw") String pw;
    @SerializedName("status") String status;
    @SerializedName("statusMessage") String statusMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
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
