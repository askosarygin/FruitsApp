package com.example.fruitsapp.handler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fruitsapp.FruitsDB;
import com.example.fruitsapp.FruitsRecyclerAdapter;
import com.example.fruitsapp.R;

public class ActionBarMenuHandler {
    public void createActionBarMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.action_bar_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item, FruitsDB fruitsDB, FruitsRecyclerAdapter fruitsRecyclerAdapter) {
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
                return true;
            default:
                return false;
        }
    }
}
