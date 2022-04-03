package com.example.fruitsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fruitsapp.handler.ActionBarMenuHandler;
import com.example.fruitsapp.handler.Handler;

public class MainActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(getMenuInflater(), getLayoutInflater());

        RecyclerView recyclerView = findViewById(R.id.fruits_recycler_view);
        recyclerView.setAdapter(handler.getFruitsRecyclerAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        handler.createActionBarMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (handler.actionBarItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}