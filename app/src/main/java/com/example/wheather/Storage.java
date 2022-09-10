package com.example.wheather;

import com.example.wheather.models.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private static final Storage STORAGE = new Storage();
    List<City> listCity = new ArrayList<>();

    private Storage() {
    }


    public static Storage getStorage(){
        return STORAGE;
    }
    public List<City> getListCity(){
        return listCity;
    }
    public City findCityByName(String str){
        return listCity.stream().filter(x->x.getName().equals(str)).collect(Collectors.toList()).get(0);
    }
    public void deleteCityByName(String str){
        try {
            listCity.remove(listCity.stream().filter(x -> x.getName().equals(str)).collect(Collectors.toList()).get(0));
        }
        catch (Exception ex){

        }
    }
}
