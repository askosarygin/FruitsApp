package com.example.fruitsapp;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SelectedItemDB {
    Map<Integer, View> selectedItems = new HashMap<>();

    public void selectItem(Integer itemId, View selector) {
        selectedItems.put(itemId, selector);
        selector.setVisibility(View.VISIBLE);
    }

    public void removeSelection(Integer itemId) {
        Objects.requireNonNull(selectedItems.get(itemId)).setVisibility(View.INVISIBLE);
        selectedItems.remove(itemId);
    }

    public void clearSelection() {
        for (View value : selectedItems.values()) {
            value.setVisibility(View.INVISIBLE);
        }
        selectedItems.clear();
    }

    public boolean isSelected(Integer itemId) {
        return selectedItems.containsKey(itemId);
    }

    public int getCountSelected() {
        return selectedItems.size();
    }

    public Map<Integer, View> getSelectedItems() {
        return selectedItems;
    }
}
