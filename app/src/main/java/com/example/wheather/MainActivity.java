package com.example.wheather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wheather.models.City;
import com.example.wheather.models.Season;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    Storage storage;
    Season season;
    TextView temperature;
    Button settings;
    City city;
    TextView type;
    List<City> cityList;
    Spinner seasonSpinner;
    Spinner citySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public void init(){
        settings = findViewById(R.id.settingsButton);
        storage = Storage.getStorage();
        type = findViewById(R.id.typeCityText);
        temperature = findViewById(R.id.temperature);
        seasonSpinner = (Spinner) findViewById(R.id.chooseSeasonSpinner);
        citySpinner= (Spinner) findViewById(R.id.chooseCitySpinner);

            List<String> citiesName = storage.getListCity().stream().map(x -> x.getName()).collect(Collectors.toList());
            try {
                System.out.println(citiesName.get(0));
            }
            catch (Exception ex){

            }
            citySpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, citiesName));


        List<String> seasonNamesForSpinner = new ArrayList<>(Arrays.asList("Весна", "Лето", "Осень", "Зима"));


        seasonSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, seasonNamesForSpinner));



        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    city = storage.findCityByName(citySpinner.getSelectedItem().toString());
                    season = city.getSeasonHashMap().get(seasonSpinner.getSelectedItem().toString());
                    type.setText("Тип города: " +  city.getType().toString());
                }


                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }



    public void goToSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
    }
    public void calculateTemp(View view){
        season = city.getSeasonHashMap().get(seasonSpinner.getSelectedItem().toString());
        temperature.setText(
                String.format("%.1f", (season.getMonthList().stream().map(x->x.getTemperature()).reduce((x, y)-> x+y)).get()/3f));

    }

}