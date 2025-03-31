package com.example.butterflyapp;

public class Butterflies {
    private Butterfly [] butterflies;

    public int getCount(){return this.butterflies.length;}
    public Butterfly getButterfly(int index){return this.butterflies[index];}

    public String [] getNames(){
        String [] names = new String[this.getCount()];
        for(int i=0; i< getCount(); i++){
            names[i] = getButterfly(i).getName();
        }
        return names;
    }
}
//use instead the xml butterflies!!!!! not this file
