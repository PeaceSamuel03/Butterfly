package com.example.butterflyapp;

import java.io.Serializable;

public class Butterfly implements Serializable {
    private String name, image, description1, description2, url;
    private int size;

    public Butterfly(String name, String image, String description1, String description2, String url, int size){
        this.name = name;
        this.image = image;
        this.description1 = description1;
        this.description2 = description2;
        this.url = url;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
