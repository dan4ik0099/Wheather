package com.example.wheather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wheather.models.City;
import com.example.wheather.models.Month;
import com.example.wheather.models.Season;
import com.example.wheather.models.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Settings extends AppCompatActivity {
    boolean gg = false;
    EditText cityName;
    Storage storage;
    String type;
    Season saveSeason;
    String save;
    Spinner typeSpinner;
    Spinner seasonSpinner;
    EditText firstMonth;
    EditText secondMonth;
    EditText thirdMonth;
    TextView firstMonthName;
    TextView secondMonthName;
    TextView thirdMonthName;

    City city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }
    public void init(){
        city = new City();
        storage = Storage.getStorage();
        firstMonth = findViewById(R.id.firstEditText);
        secondMonth = findViewById(R.id.secondEditText);
        thirdMonth = findViewById(R.id.thirdEditText);
        firstMonthName = findViewById(R.id.firstMonthName);
        secondMonthName = findViewById(R.id.secondMonthName);
        thirdMonthName = findViewById(R.id.thirdMonthName);
        cityName = findViewById(R.id.cityNameText);
        typeSpinner = (Spinner) findViewById(R.id.spinner);
        typeSpinner.setAdapter(new ArrayAdapter<Type>(this, R.layout.spinner_item, Type.values()));

        type = typeSpinner.getSelectedItem().toString();


        seasonSpinner = (Spinner) findViewById(R.id.seasonSpinner);
        List<String> seasonNamesForSpinner = new ArrayList<>(Arrays.asList("Весна", "Лето", "Осень", "Зима"));

        seasonSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, seasonNamesForSpinner));

        seasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (gg) {
                    saveQuick();
                }
                else{
                    gg = true;
                }
                save = seasonSpinner.getSelectedItem().toString();

                Season newSeason = city.getSeasonHashMap().get(seasonSpinner.getSelectedItem().toString());
                List<Month> Month = new ArrayList<Month>(newSeason.getMonthList());
                firstMonthName.setText(Month.get(0).getName());
                secondMonthName.setText(Month.get(1).getName());
                thirdMonthName.setText(Month.get(2).getName());
                firstMonth.setText(String.valueOf(Month.get(0).getTemperature()));
                secondMonth.setText(String.valueOf(Month.get(1).getTemperature()));
                thirdMonth.setText(String.valueOf(Month.get(2).getTemperature()));



            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void saveCity(View view){
        type = typeSpinner.getSelectedItem().toString();
        if (!cityName.getText().toString().equals("")) {
            saveQuick();

            switch (type) {
                case "Малый":
                    city.setType(Type.SMALL);
                    break;
                case "Средний":
                    city.setType(Type.MEDIUM);
                    break;
                case "Крупный":
                    city.setType(Type.LARGE);
                    break;
                default:
                    break;
            }

            city.setName(cityName.getText().toString());
            storage.deleteCityByName(cityName.getText().toString());
            storage.getListCity().add(city);

            city = new City();
            firstMonth.setText("0");
            secondMonth.setText("0");
            thirdMonth.setText("0");
            cityName.setText("");




        }
    }
    public void goToMain(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void saveQuick(){
        saveSeason = city.getSeasonHashMap().get(save);


        if(firstMonth.getText().toString().equals("")){
            firstMonth.setText("0");
        }
        if(secondMonth.getText().toString().equals("")){
            firstMonth.setText("0");
        }
        if(thirdMonth.getText().toString().equals("")){
            firstMonth.setText("0");
        }
        saveSeason.getMonthList().get(0).setTemperature(Integer.parseInt(firstMonth.getText().toString()));
        saveSeason.getMonthList().get(1).setTemperature(Integer.parseInt(secondMonth.getText().toString()));
        saveSeason.getMonthList().get(2).setTemperature(Integer.parseInt(thirdMonth.getText().toString()));


    }
}