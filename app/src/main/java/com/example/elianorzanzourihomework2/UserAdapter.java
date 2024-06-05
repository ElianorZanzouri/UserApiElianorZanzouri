package com.example.elianorzanzourihomework2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<UserData> dataSet;

    public UserAdapter(List<UserData> dataSet) {
        this.dataSet = dataSet;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserData userData=dataSet.get(position);
        holder.textViewFirst.setText(userData.firstName);
        holder.textViewLast.setText(userData.lastName);
        holder.textViewCountry.setText(userData.country);
        holder.textViewCity.setText(userData.city);
        Glide.with(holder.itemView.getContext()).load(userData.picture)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
