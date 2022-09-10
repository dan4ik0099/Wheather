package com.example.wheather.models;

import androidx.room.Entity;

import java.util.HashMap;
import java.util.Map;
@Entity
public class City {

    String name;
    Map<String, Season> seasonHashMap = new HashMap<String, Season>();
    Enum<Type> type;


    public City() {
        this.type = Type.SMALL;
        this.name = "...";
        Month june = new Month("Июнь", 0);
        Month july = new Month("Июль", 0);
        Month august = new Month("Август", 0);
        Season summer = new Season("Лето", june, july, august);

        Month september = new Month("Сентябрь", 0);
        Month october = new Month("Октябрь", 0);
        Month november = new Month("Ноябрь", 0);
        Season autumn = new Season("Осень", september, october, november);

        Month december = new Month("Декабрь", 0);
        Month january = new Month("Январь", 0);
        Month february = new Month("Февраль", 0);
        Season winter = new Season("Зима", december, january, february);

        Month march = new Month("Март", 0);
        Month april = new Month("Апрель", 0);
        Month may = new Month("Май", 0);
        Season spring = new Season("Весна", march, april, may);
        this.seasonHashMap.put("Лето", summer);
        this.seasonHashMap.put("Осень", autumn);
        this.seasonHashMap.put("Зима", winter);
        this.seasonHashMap.put("Весна", spring);
    }

    public Enum<Type> getType() {
        return type;
    }

    public void setType(Enum<Type> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Season> getSeasonHashMap() {
        return seasonHashMap;
    }

    public void setSeasonHashMap(HashMap<String, Season> seasonHashMap) {
        this.seasonHashMap = seasonHashMap;
    }
}
