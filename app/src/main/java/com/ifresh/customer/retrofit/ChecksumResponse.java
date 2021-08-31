package com.ifresh.customer.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChecksumResponse {
    @SerializedName("checksum")
    @Expose
    private String checksum;
    @SerializedName("token")
    @Expose
    private String token;

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
