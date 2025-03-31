package com.example.butterflyapp;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLButterflies {

    private Butterfly [] butterflies;
    private Context context;

    public XMLButterflies(Context context){
        this.context = context;

        //make an input stream, doc builder and xml doc
        InputStream stream = null;
        DocumentBuilder builder = null;
        Document xmldoc = null;

        //file is located in the res/raw directory
        try{
            stream = this.context.getResources().openRawResource(R.raw.butterfly_data);
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = builder.parse(stream);
        } catch (Exception e){
            //exception handler
        }

        //slice xmldoc into 4 node lists
        NodeList nameList = xmldoc.getElementsByTagName("name");
        NodeList description1List = xmldoc.getElementsByTagName("description1");
        NodeList description2List = xmldoc.getElementsByTagName("description2");
        NodeList imageList = xmldoc.getElementsByTagName("image");
        NodeList urlList = xmldoc.getElementsByTagName("url");
        NodeList sizeList = xmldoc.getElementsByTagName("size");

        //traverse these lists
        this.butterflies = new Butterfly[nameList.getLength()];

        for (int i = 0; i <nameList.getLength(); i++){
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String description1 = description1List.item(i).getFirstChild().getNodeValue();
            String description2 = description2List.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();
            int size = Integer.parseInt(sizeList.item(i).getFirstChild().getNodeValue());

            this.butterflies[i] = new Butterfly(name, image, description1, description2, url, size);
        }
    }

    //get full list of butterflies
    public List<Butterfly> getButterflies(){
        return Arrays.asList(this.butterflies); //convert to list
    }

    public  int getCount(){return this.butterflies.length;}
    public Butterfly getButterfly(int index){return this.butterflies[index];}

    public String [] getNames(){
        String [] names = new String[this.getCount()];
        for (int i=0; i < getCount(); i++){
            names[i] = getButterfly(i).getName();
        }
        return names;
    }
}
