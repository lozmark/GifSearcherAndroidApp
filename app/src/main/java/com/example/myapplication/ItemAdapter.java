package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    List<String> name;
    List<String> email;
    Context context;

    List<String> gifs;
    public ItemAdapter(List<String> name, List<String> email,List<String> gifs , Context context) {
        this.name = name;
        this.email = email;
        this.context = context;
        this.gifs=gifs;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(name.get(position));
        holder.email.setText(email.get(position));
        Glide.with(context).load(gifs.get(position)).into(holder.view);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class child_activity = GifInfoActivity.class;
                Intent intent = new Intent(context,child_activity);
                intent.putExtra("name", holder.name.getText().toString());
                intent.putExtra("email", holder.email.getText().toString());
                intent.putExtra("gif", gifs.get(position));
                context.startActivity(intent);
            }
        };
        holder.view.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView view;
        TextView name;
        TextView email;

        public ItemViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.gifka);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
