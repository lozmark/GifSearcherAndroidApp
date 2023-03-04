package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private EditText search_field;
    private Button search_button;

    private RecyclerView list;
    private ItemAdapter itemAdapter;
    List<String> name = new ArrayList<>();
    List<String> email = new ArrayList<>();

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
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(responce);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        }
    }


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
        try {
        JSONObject jsonObject = new JSONObject(jsonDataFromAsset());
        JSONArray array = jsonObject.getJSONArray("users");
        for (int i = 0; i< array.length(); i++){
            JSONObject userdata = array.getJSONObject(i);
            name.add((String) userdata.get("name"));
            email.add((String) userdata.get("email"));

        }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        itemAdapter = new ItemAdapter(name,email,this);
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
    public String jsonDataFromAsset() throws IOException {
        AssetManager manager = getAssets();
        InputStream is = manager.open("file1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        while (reader.ready()){
            builder.append(reader.readLine());
        }
        return builder.toString();
    }
}