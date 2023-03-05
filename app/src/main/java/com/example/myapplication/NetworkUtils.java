package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetworkUtils {

    private static final String API_BASE_URL = "https://api.giphy.com/";
    private static final String API_SEARCH_URL = "v1/gifs/search";
    private static final String API_KEY = "api_key";
    private static final String param_word = "q";
    public static URL generateURL(String word){
        Uri builduri = Uri.parse(API_BASE_URL+API_SEARCH_URL)
                .buildUpon()
                .appendQueryParameter(API_KEY, "4moKG5js9h01JshUUIjYjcNUUGmzJXFG")
                .appendQueryParameter(param_word, word)
                .appendQueryParameter("limit", "25")
                .appendQueryParameter("offset", "0")
                .appendQueryParameter("rating", "g")
                .appendQueryParameter("lang", "en").build();
        URL url = null;
        try {
            url= new URL(builduri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
    public static String getResponceFromURL(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream in = httpURLConnection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        boolean hasInput = scanner.hasNext();
        if (hasInput){
            return scanner.next();
        }
        else {
            return null;
        }
    }

}
