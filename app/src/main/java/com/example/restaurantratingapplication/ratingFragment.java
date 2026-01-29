package com.example.restaurantratingapplication;




import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;


import androidx.fragment.app.DialogFragment;

public class ratingFragment extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            final View view = inflater.inflate(R.layout.ratingbox,container);
            RatingBar ratingBar = view.findViewById(R.id.ratingBarID);
            Button saveButton = view.findViewById(R.id.saveRateButtonID);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double ratingSel = ratingBar.getRating();

                    MainActivity main = (MainActivity) getActivity();
                    if (main != null) {
                        main.sendRating(ratingSel);
                }
                    dismiss();

            }});

            return view;

        }
    }

