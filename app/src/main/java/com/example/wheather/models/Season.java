package com.example.wheather.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

public class Season {
    String name;
    List<Month> monthList = new ArrayList<>();

    public Season(String name, Month first, Month second, Month third) {
        this.name = name;
        monthList.add(first);
        monthList.add(second);
        monthList.add(third);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Month> getMonthList() {
        return monthList;
    }

    public void setMonthHashMap(List<Month> monthHashMap) {
        this.monthList = monthHashMap;
    }
}
