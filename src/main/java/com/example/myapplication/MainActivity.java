package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("");
        System.out.println("Reference = "+ reference);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, Object>> typeIndicator = new GenericTypeIndicator<HashMap<String, Object>>(){};
                HashMap<String, Object> dataMap = dataSnapshot.getValue(typeIndicator);
                System.out.println("data = "+ dataMap);
                HashMap<String, Object> dataMap2 = (HashMap<String, Object>) dataMap.get("health monitoring");
                TextView textViewData = findViewById(R.id.textView3);
                textViewData.setText(dataMap2.get("heartrate").toString());
                TextView textViewData2 = findViewById(R.id.textView5);
                textViewData2.setText(dataMap2.get("spo2level").toString());
                TextView textViewData3 = findViewById(R.id.textView7);
                textViewData3.setText(dataMap2.get("temprature").toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                 This method is called if the database operation is cancelled
//                 Handle the error gracefully if needed
            }
        });


    }
}