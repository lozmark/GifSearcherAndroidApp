package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private int numberItems;

    public ItemAdapter(int numberItems){
        this.numberItems = numberItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview);
        }
        public void bind(String text){
            textView.setText(text);
        }
    }
}
