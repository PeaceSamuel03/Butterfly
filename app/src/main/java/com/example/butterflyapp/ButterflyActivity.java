package com.example.butterflyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ButterflyActivity extends AppCompatActivity {

    //need to add description1
    TextView butterflyTV = null;
    TextView butterflyTV2 = null;
    ImageView butterflyIM = null;
    Button moreinfoBT = null;
    Button backBT = null;

    Butterfly butterfly = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly);

        //wire widgets with the objects
        butterflyIM = findViewById(R.id.imageView2);
        butterflyTV = findViewById(R.id.textView);
        butterflyTV2 = findViewById(R.id.textView2);
        moreinfoBT = findViewById(R.id.button);
        backBT = findViewById(R.id.button3);

        //get data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        butterfly = (Butterfly) bundle.getSerializable("butterfly");

        //populate object with the data
        butterflyTV.setText(butterfly.getName());
        butterflyTV2.setText(butterfly.getDescription1());

        String imageName = butterfly.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));
        int imageId = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
        butterflyIM.setImageResource(imageId);

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this should navigate back to the previous Activity
                onBackPressed();
            }
        });


        moreinfoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make an explicit intent and bndle
                Intent intent = new Intent(ButterflyActivity.this, DetailsActivity2.class);
                Bundle bundle = new Bundle();

                //put data into bundle and the bundle into intent
                bundle.putSerializable("butterfly", butterfly);
                intent.putExtras(bundle);

                //start activity
                startActivity(intent);
            }
        });
    }
}
