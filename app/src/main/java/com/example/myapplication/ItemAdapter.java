package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    List<String> name;
    List<String> email;
    Context context;

    public ItemAdapter(List<String> name, List<String> email, Context context) {
        this.name = name;
        this.email = email;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.name.setText(name.get(position));
        holder.email.setText(email.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView email;

        public ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
