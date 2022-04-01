package com.example.fruitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FruitsDB fruitsDB = new FruitsDB();
        LayoutInflater inflater = getLayoutInflater();
        FruitsRecyclerAdapter fruitsRecyclerAdapter = new FruitsRecyclerAdapter(fruitsDB, inflater);
        RecyclerView recyclerView = findViewById(R.id.fruits_recycler_view);
        recyclerView.setAdapter(fruitsRecyclerAdapter);
    }
}