package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GifInfoActivity extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_info);
        name = findViewById(R.id.name_child);
        email = findViewById(R.id.email_child);
        gif = findViewById(R.id.gif_in_child);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        email.setText(intent.getStringExtra("email"));
        Glide.with(this).load(intent.getStringExtra("gif")).into(gif);
    }
}