package com.example.fruitsapp;

public class Fruit {
    private static int number = 0;

    private String name;
    private String description;
    private int avatarId;

    private final long id;
    private boolean isSelected;

    public Fruit(String name, String description, int avatarId) {
        this.name = name;
        this.description = description;
        this.avatarId = avatarId;
        this.isSelected = false;
        this.id = number;
        number++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public void setSelection() {
        isSelected = true;
    }

    public boolean getSelection() {
        return isSelected;
    }
}
