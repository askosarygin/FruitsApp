package com.example.fruitsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

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
            fruitsDB.addFruit("Новый фрукт", "Это новый фрукт", R.drawable.fruits);
            fruitsRecyclerAdapter.notifyItemInserted(fruitsDB.getDBSize() + 1);
            return true;
            case R.id.action_bar_clear:
                for (int i = 0; i < fruitsDB.getDBSize(); i++) {
                    fruitsRecyclerAdapter.notifyItemRemoved(0);
                }
                fruitsDB.clearDB();
        }
        return super.onOptionsItemSelected(item);
    }

    FruitsRecyclerAdapter.IMainActivity IMainActivity = new FruitsRecyclerAdapter.IMainActivity() {
        @Override
        public ActionMode activateContextualMenu(FruitsRecyclerAdapter.ActionModeCallback actionModeCallback) {
            return startSupportActionMode(actionModeCallback);
        }
    };
}