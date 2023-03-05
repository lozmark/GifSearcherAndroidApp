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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText search_field;
    private Button search_button;

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    List<String> id = new ArrayList<>();
    List<String> title = new ArrayList<>();

    List<String> gif_links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        search_field = findViewById(R.id.search_field);
        search_button = findViewById(R.id.button_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(id, title, gif_links, this);
        recyclerView.setAdapter(itemAdapter);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(NetworkUtils.generateURL(String.valueOf(search_field.getText()))).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponce = response.body().string();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    id.clear();
                                    title.clear();
                                    gif_links.clear();
                                    recyclerView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            itemAdapter.notifyDataSetChanged();
                                        }
                                    });
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(myResponce);
                                        JSONArray array = jsonObject.getJSONArray("data");
                                        for (int i = 0; i < array.length(); i++) {
                                            JSONObject gifdata = array.getJSONObject(i);
                                            id.add((String) gifdata.get("id"));
                                            title.add((String) gifdata.get("title"));
                                            JSONObject images = gifdata.getJSONObject("images").getJSONObject("original");
                                            gif_links.add((String) images.get("url"));
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        };
        search_button.setOnClickListener(onClickListener);
    }
}