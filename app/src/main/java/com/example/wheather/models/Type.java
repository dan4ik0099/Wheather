package com.example.wheather.models;

public enum Type{
    SMALL("Малый"),
    MEDIUM("Средний"),
    LARGE("Крупный");

    private String friendlyName;

    private Type(String friendlyName){
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString(){
        return friendlyName;
    }
}
