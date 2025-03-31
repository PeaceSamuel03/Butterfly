package com.example.butterflyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private XMLButterflies butterflies;
    RecyclerViewInterface recyclerViewInterface;
    private Set<String> favoriteButterflies;

    public CustomRecyclerAdapter(Context context, int layout, XMLButterflies butterflies, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.layout = layout;
        this.butterflies = butterflies;
        this.recyclerViewInterface = recyclerViewInterface;
        loadFavorites();

    }

    private void loadFavorites() {
        SharedPreferences prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        favoriteButterflies = prefs.getStringSet("favorite_butterflies", new HashSet<>());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(this.context).inflate(layout, parent, false);
        return new CustomRecyclerAdapter.ViewHolder(row, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.label.setText(butterflies.getButterfly(position).getName());

        String imageName = butterflies.getButterfly(position).getImage();
        imageName = imageName.substring(0,imageName.indexOf("."));
        int imageId = this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());


        holder.icon.setImageResource(imageId);

        // Check if the butterfly is a favorite
        String butterflyName = butterflies.getButterfly(position).getName();
        if (favoriteButterflies.contains(butterflyName)) {
            holder.imageButton.setImageResource(R.drawable.heart_fill); // Set filled icon if favorite
        } else {
            holder.imageButton.setImageResource(R.drawable.heart_nofill); // Set empty icon if not favorite
        }

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteButterflies.contains(butterflyName)) {
                    favoriteButterflies.remove(butterflyName); // Remove from favorites
                    holder.imageButton.setImageResource(R.drawable.heart_nofill); // Update icon
                } else {
                    favoriteButterflies.add(butterflyName); // Add to favorites
                    holder.imageButton.setImageResource(R.drawable.heart_fill); // Update icon
                }
                saveFavorites(); // Save updated favorites to SharedPreferences
            }
        });

    }

    private void saveFavorites() {
        SharedPreferences prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("favorite_butterflies", favoriteButterflies);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return this.butterflies.getCount();
    }


    // inner static ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton imageButton;
        TextView label;
        ImageView icon;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            label = itemView.findViewById(R.id.textView);
            icon = itemView.findViewById(R.id.imageView);
            imageButton = itemView.findViewById(R.id.imageButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    recyclerViewInterface.onItemClick(position);
                }
            });
        }
    }
}
