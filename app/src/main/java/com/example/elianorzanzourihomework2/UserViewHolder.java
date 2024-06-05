package com.example.elianorzanzourihomework2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewFirst;
    TextView textViewLast;
    TextView textViewCity;
    TextView textViewCountry;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageViewUserImage);
        textViewFirst=itemView.findViewById(R.id.textViewUserFirstName);
        textViewLast=itemView.findViewById(R.id.textViewUserLastName);
        textViewCountry=itemView.findViewById(R.id.textViewUserCountry);
        textViewCity=itemView.findViewById(R.id.textViewUserCity);
    }
}
