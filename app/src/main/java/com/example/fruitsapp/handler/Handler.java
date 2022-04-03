package com.example.fruitsapp.handler;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fruitsapp.FruitsDB;
import com.example.fruitsapp.FruitsRecyclerAdapter;

public class Handler {
    private final MenuInflater menuInflater;
    private final LayoutInflater layoutInflater;

    private final FruitsDB fruitsDB;
    private final FruitsRecyclerAdapter fruitsRecyclerAdapter;
    private final ActionBarMenuHandler actionBarMenuHandler;

    public Handler(MenuInflater menuInflater, LayoutInflater layoutInflater) {
        this.menuInflater = menuInflater;
        this.layoutInflater = layoutInflater;

        fruitsDB = new FruitsDB();
        fruitsRecyclerAdapter = new FruitsRecyclerAdapter(fruitsDB, layoutInflater);
        actionBarMenuHandler = new ActionBarMenuHandler();
    }

    public FruitsRecyclerAdapter getFruitsRecyclerAdapter() {
        return fruitsRecyclerAdapter;
    }

    public void createActionBarMenu(Menu menu) {
        actionBarMenuHandler.createActionBarMenu(menu, menuInflater);
    }

    public boolean actionBarItemSelected(MenuItem item) {
        return actionBarMenuHandler.onOptionsItemSelected(item, fruitsDB, fruitsRecyclerAdapter);
    }
}
