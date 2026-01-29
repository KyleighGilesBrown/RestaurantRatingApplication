package com.example.restaurantratingapplication;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

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
    }
}