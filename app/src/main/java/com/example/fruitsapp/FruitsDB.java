package com.example.fruitsapp;

import java.util.ArrayList;
import java.util.List;

public class FruitsDB {
    private final List<Fruit> fruits;

    public FruitsDB() {
        this.fruits = new ArrayList<>();
        fruits.add(new Fruit("Яблоко", "Вкусный фрукт, который может упасть на голову и заставить тебя совершить открытие", R.drawable.apple));
        fruits.add(new Fruit("Апельсин", "Много нас, а он один. Хороший и вкусный источник витамина С", R.drawable.orange));
        fruits.add(new Fruit("Груша", "Все хотят её скушать. Похожа на лампочку. Висит на дереве как и яблоко и тоже может помочь с открытиями", R.drawable.pear));
        fruits.add(new Fruit("Слива", "Вкусная когда спелая, растет на деревьях", R.drawable.plum));
        fruits.add(new Fruit("Гранат", "Источник железа, вкусный фрукт. Соус из нее добавляли в шаурму в Подольске", R.drawable.pomegranate));
    }

    public Fruit getFruit(int position) {
        return fruits.get(position);
    }

    public int getDBSize() {
        return fruits.size();
    }

    public void addFruit(String name, String description, int avatarId) {
        fruits.add(new Fruit(name, description, avatarId));
    }

    public boolean deleteFruitById(int id) {
        for (Fruit fruit : fruits) {
            if (fruit.getId() == id) {
                fruits.remove(fruit);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFruit(int position) {
        fruits.remove(position);
        return true;
    }

    public boolean clearDB() {
        fruits.clear();
        return true;
    }

    public void deleteSelectedFruits() {
        List<Fruit> selectedFruits = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.isSelected()) {
                selectedFruits.add(fruit);
            }
        }
        for (Fruit selectedFruit : selectedFruits) {
            fruits.remove(selectedFruit);
        }
    }

    public void clearSelectionFruits() {
        for (Fruit fruit : fruits) {
            if (fruit.isSelected()) {
                fruit.toggleSelection(false);
            }
        }
    }
}
