package com.example.Mypage;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {
    @SerializedName("name") String name;
    @SerializedName("updateName") String updateName;
    @SerializedName("updatePw") String updatePw;

    public UpdateRequest(String name){
        this.name = name;
    }

    public UpdateRequest(String name, String updateName, String updatePw){
        this.name = name;
        this.updateName = updateName;
        this.updatePw = updatePw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdatePw() {
        return updatePw;
    }

    public void setUpdatePw(String updatePw) {
        this.updatePw = updatePw;
    }
}
