package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView view;
    private EditText search_field;
    private Button search_button;

    private RecyclerView list;
    private ItemAdapter itemAdapter;
    List<String> name = new ArrayList<>();
    List<String> email = new ArrayList<>();

    List<String> gif_links = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.rv);
        search_field = findViewById(R.id.search_field);
        search_button = findViewById(R.id.button_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);
        itemAdapter = new ItemAdapter(name,email,gif_links,this);
        list.setAdapter(itemAdapter);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                URL genURL = NetworkUtils.generateURL(search_field.getText().toString());

                new GiphyTask().execute(genURL);
            }
        };
        search_button.setOnClickListener(onClickListener);

    }

    class GiphyTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String responce = null;
            try {
                responce = NetworkUtils.getResponceFromURL(urls[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return responce;
        }

        @Override
        protected void onPostExecute(String responce) {
            name.clear();
            email.clear();
            gif_links.clear();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(responce.toString());
                JSONArray array = jsonObject.getJSONArray("data");
                for (int i = 0; i< array.length(); i++){
                    JSONObject gifdata = array.getJSONObject(i);
                    name.add((String) gifdata.get("id"));
                    email.add((String) gifdata.get("title"));
                    JSONObject images = gifdata.getJSONObject("images").getJSONObject("original");
                    gif_links.add((String) images.get("url"));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}