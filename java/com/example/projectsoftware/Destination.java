package com.example.projectsoftware;

public class Destination {
    private int id;
    private int imageResId;
    private String name;
    private String location;
    private boolean isFavorite;

    public Destination(int id, int imageResId, String name, String location) {
        this.id = id;
        this.imageResId = imageResId;
        this.name = name;
        this.location = location;
        this.isFavorite = false;
    }

    public int getId() {
        return id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
