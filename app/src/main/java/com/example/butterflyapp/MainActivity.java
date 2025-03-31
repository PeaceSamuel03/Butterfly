package com.example.butterflyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView list = null;
    CustomRecyclerAdapter adapter = null;
    XMLButterflies butterflies = null;
//    SearchView searchView = null;
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner);
//        searchView = findViewById(R.id.searchbar);
        butterflies = new XMLButterflies(this);

        adapter = new CustomRecyclerAdapter(this, R.layout.row_layout, butterflies, this);


        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        //populate spinner with the sorting options
        String[] sortingOptions = {"Sort Alphabetically", "Sort by Butterfly size"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortingOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //spinner listener for sorting
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = adapterView.getItemAtPosition(i).toString();
                switch (selectedOption){
                    case "Sort Alphabetically":
                        sortButterfliesAlphabetically();
                        break;
                    case "Sort by Butterfly size":
                        sortButterfliesBySize();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing

            }
        });

    }

    private void sortButterfliesAlphabetically(){
        Collections.sort(butterflies.getButterflies(),new Comparator<Butterfly>(){
            @Override
            public int compare(Butterfly b1, Butterfly b2){
                return b1.getName().compareToIgnoreCase(b2.getName());
            }
        });
        adapter.notifyDataSetChanged(); //update adapter to update recycler view
        Toast.makeText(this, "Sorted Alphabetically", Toast.LENGTH_SHORT).show();
    }

    private void sortButterfliesBySize(){
        Collections.sort(butterflies.getButterflies(), new Comparator<Butterfly>() {
            @Override
            public int compare(Butterfly b1, Butterfly b2) {
                return Integer.compare(b1.getSize(), b2.getSize());
            }
        });
        adapter.notifyDataSetChanged(); // update adapter change recycler view
        Toast.makeText(this, "Sorted by Butterfly size", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onItemClick(int position){
        Toast.makeText(this, butterflies.getButterfly(position).getName(), Toast.LENGTH_SHORT).show();

        //make intent and bundle
        Intent intent = new Intent(MainActivity.this, ButterflyActivity.class);
        Bundle bundle = new Bundle();

        //crop the data for the intent, take the butterfly from the array
        Butterfly butterfly = butterflies.getButterfly(position);

        //place data into bundle, place bundle into the intent
        bundle.putSerializable("butterfly", butterfly);
        intent.putExtras(bundle);

        //start Activity
        startActivity(intent);
    }
}