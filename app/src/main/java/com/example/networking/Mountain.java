package com.example.networking;

public class Mountain {
    private String ID;
    private String name;
    @SerializedName("cost")
    private int feet;
    @SerializedName("size")
    private int meter;

    public String getName() {
        return name;
    }
}