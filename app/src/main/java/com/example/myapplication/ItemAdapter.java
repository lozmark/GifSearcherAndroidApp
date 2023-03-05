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

    List<String> id;
    List<String> title;
    Context context;

    List<String> gifs;
    public ItemAdapter(List<String> id, List<String> title,List<String> gifs , Context context) {
        this.id = id;
        this.title = title;
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

        holder.id.setText(id.get(position));
        holder.title.setText(title.get(position));
        Glide.with(context).load(gifs.get(position)).into(holder.view);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class child_activity = GifInfoActivity.class;
                Intent intent = new Intent(context,child_activity);
                intent.putExtra("id", holder.id.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("gif", gifs.get(position));
                context.startActivity(intent);
            }
        };
        holder.view.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView view;
        TextView id;
        TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.gifka);
            id= itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
        }
    }
}
