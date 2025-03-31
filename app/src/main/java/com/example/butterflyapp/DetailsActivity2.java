package com.example.butterflyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity2 extends AppCompatActivity {

    //gonna try to make it a list of scrollable image in this activity
    TextView nameTV, descriptionTV;
    ImageView butterflyIM = null;
    Button webinfoBT = null;
    Button backBT = null;

    Butterfly butterfly = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly_details2);

        //wire
        nameTV = findViewById(R.id.nametextView2);
        descriptionTV = findViewById(R.id.descriptiontextView2);
        butterflyIM = findViewById(R.id.imageView4);
        webinfoBT = findViewById(R.id.button2);
        backBT = findViewById(R.id.button4);

        //get data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        butterfly = (Butterfly) bundle.getSerializable("butterfly");

        //populate widgets with data
        nameTV.setText(butterfly.getName());
        descriptionTV.setText(butterfly.getDescription2());

        String imageName = butterfly.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));
        int imageId = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
        butterflyIM.setImageResource(imageId);

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); //previous page
            }
        });

        webinfoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity2.this, WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", butterfly.getUrl());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
