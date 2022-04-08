package com.example.fruitsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FruitsDB fruitsDB;
    private FruitsRecyclerAdapter fruitsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruitsDB = new FruitsDB();
        LayoutInflater inflater = getLayoutInflater();
        fruitsRecyclerAdapter = new FruitsRecyclerAdapter(fruitsDB, inflater, IMainActivity);
        RecyclerView recyclerView = findViewById(R.id.fruits_recycler_view);
        recyclerView.setAdapter(fruitsRecyclerAdapter);

        Button popupButton = findViewById(R.id.button_open_popup);
        popupButton.setOnClickListener(v -> showPopupMenu(popupButton));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_add:
                addNewFruit();
                return true;
            case R.id.action_bar_clear:
                clearFruits();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    FruitsRecyclerAdapter.IMainActivity IMainActivity = actionModeCallback -> startSupportActionMode(actionModeCallback);

    @SuppressLint("NonConstantResourceId")
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.popup_add:
                    addNewFruit();
                    return true;
                case R.id.popup_clear:
                    clearFruits();
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    public void addNewFruit() {
        fruitsDB.addFruit("Новый фрукт", "Это новый фрукт", R.drawable.fruits);
        fruitsRecyclerAdapter.notifyItemInserted(fruitsDB.getDBSize() + 1);
    }

    public void clearFruits() {
        for (int i = 0; i < fruitsDB.getDBSize(); i++) {
            fruitsRecyclerAdapter.notifyItemRemoved(0);
        }
        fruitsDB.clearDB();
    }
}