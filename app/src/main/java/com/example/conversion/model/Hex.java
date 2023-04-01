package com.example.conversion.model;

import com.google.gson.annotations.SerializedName;

public class Hex {
    @SerializedName("hex")
    private String hex;

    public Hex(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
