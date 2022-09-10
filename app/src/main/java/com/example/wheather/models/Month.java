package com.example.wheather.models;

public class Month {
    String name;
    int temperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Month(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }
}
