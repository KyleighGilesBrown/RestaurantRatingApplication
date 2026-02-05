package com.example.restaurantratingapplication;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private Restaurant currentRestaurant;
    private Dish currentDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ratingButton();
        currentDish = new Dish();
        currentRestaurant = new Restaurant();
        saveMainButton();
    }

    private void ratingButton() {
        Button ratingButton = findViewById(R.id.ratingButtonID);
        ratingButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                ratingFragment rating = new ratingFragment();
                rating.show(fm,"rating");
            }
        });
    }
    public void sendRating(double ratingSel) {
        TextView textView = findViewById(R.id.ratingViewID);
        textView.setText(String.valueOf(ratingSel) + "/5");
        currentDish.setRating(ratingSel);
    }

    private void saveMainButton() {
        Button saveRest = findViewById(R.id.saveButtonID);
        saveRest.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                EditText restNameField = findViewById(R.id.editTextRestNameID);
                EditText strAddressField = findViewById(R.id.editTextStrAddress);
                EditText cityField = findViewById(R.id.editTextCityAddress);
                EditText stateField = findViewById(R.id.editTextStateAddress);
                EditText zipField = findViewById(R.id.editTextZipAddress);

                EditText dishNameField = findViewById(R.id.editTextDishNameID);
                EditText dishTypeField = findViewById(R.id.editTextDishTypeID);
                TextView ratingView = findViewById(R.id.ratingViewID);

                currentRestaurant.setRestName(restNameField.getText().toString());
                currentRestaurant.setStrAddress(strAddressField.getText().toString());
                currentRestaurant.setCity(cityField.getText().toString());
                currentRestaurant.setState(stateField.getText().toString());
                currentRestaurant.setZipCode(zipField.getText().toString());

                currentDish.setDishName(dishNameField.getText().toString());
                currentDish.setDishType(dishTypeField.getText().toString());

                boolean wasSuccessful;
                RatingDataSource ds = new RatingDataSource(MainActivity.this);

                try{
                    ds.open();
                    if(currentRestaurant.getRestaurantID() == -1) {
                        wasSuccessful = ds.insertRestaurant(currentRestaurant);
                        int newId = ds.getLastRestaurantID();
                        currentRestaurant.setRestaurantID(newId);
                    }
                    else {
                        wasSuccessful = ds.updateRestaurant(currentRestaurant);

                    }
                    if(wasSuccessful && currentRestaurant.getRestaurantID() != -1) {
                        currentDish.setRestaurantID(currentRestaurant.getRestaurantID());

                        if(currentDish.getDishID() == -1) {
                            wasSuccessful = ds.insertDish(currentDish);
                            if(wasSuccessful) {
                                int newDishId = ds.getLastDishID();
                                currentDish.setDishID(newDishId);
                            }
                        }
                        else {
                            wasSuccessful = ds.updateDish(currentDish);
                        }
                    }

                    ds.close();

                }
                catch (Exception e){
                    wasSuccessful = false;
                }

            }
        });

    }
}