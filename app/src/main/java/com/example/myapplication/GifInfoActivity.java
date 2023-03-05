package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GifInfoActivity extends AppCompatActivity {
    private TextView id;
    private TextView title;
    private ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_info);
        id = findViewById(R.id.id_child);
        title = findViewById(R.id.title_child);
        gif = findViewById(R.id.gif_in_child);

        Intent intent = getIntent();
        id.setText(intent.getStringExtra("id"));
        title.setText(intent.getStringExtra("title"));
        Glide.with(this).load(intent.getStringExtra("gif")).into(gif);
    }
}