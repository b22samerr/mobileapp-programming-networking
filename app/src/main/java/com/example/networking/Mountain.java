package com.example.networking;

import android.content.ClipData;

import com.google.gson.annotations.*;

public class Mountain {
    private String ID;
    private String name;
    @SerializedName("cost")
    private int feet;
    @SerializedName("size")
    private int meter;

    public Mountain(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}