package com.example.dadmakeafunny;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JokesReader {
/* class opens connection to reddit and handles all jokes */

    private String url;
    private ArrayList<Joke> jokes;
    private String last_joke_id;
    private static final String BASE_URL = "https://www.reddit.com/r/dadjokes/.json";
    private static final String LIMIT_FLAG  = "?limit=";
    private int limit = 20;

    /*constructor*/
    public JokesReader(){
        this.jokes = new ArrayList<>();
        this.last_joke_id = null;
    }


    /*starts the connection to subreddit and pulls in first 20 jokes*/

    public void startConnection(){
        this.url = createUrl();
        InputStream response = this.sendGet(url);
        String data = this.readConnection(response);
        this.pullJokes(data);
    }


    //returns an InputStream from the URL
    private InputStream sendGet(String url){
        InputStream response = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(this.url).openConnection();
            connection.setRequestProperty("User-Agent", "Dad-make-a-funny v1.0");
            response = connection.getInputStream();
        }catch (Exception e){
            Log.e("sendGet()", "Connection error: " + e.toString());
        }

        //response is being returned as null. android.os.NetworkOnMainThreadException getting this
        //currently reading up on this and how to fix it. Its an android issue.

        return response;
    }


    //create a url based on if accessing first time or new access
    private String createUrl(){
        if (this.last_joke_id == null){
            return (this.BASE_URL + this.LIMIT_FLAG + this.limit);
        }
        else{
            return (this.BASE_URL + "?after=" + this.last_joke_id + this.LIMIT_FLAG + this.limit);
        }
    }

    //return contents of connection as a string
    private String readConnection(InputStream response){
        String result = "";
        String tmp;
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        try {
            while( (tmp = br.readLine()) != null){
                result += tmp + "\n";
            }
            br.close();
            return result;
        }catch (IOException e){
            Log.d("readConnection()", e.toString());
            return null;
        }
    }

    //coverts to JSON and read jokes from it
    private void pullJokes(String data) {
        try {
            JSONObject raw_data = new JSONObject(data).getJSONObject("data");
            JSONArray children = raw_data.getJSONArray("children");
            this.last_joke_id = raw_data.getString("after");
            for (int i = 0; i < children.length(); i++){
                JSONObject current_json = children.getJSONObject(i).getJSONObject("data");

                Joke curr_joke = new Joke();
                curr_joke.setTitle(current_json.optString("title"));
                curr_joke.setLink(current_json.optString("url"));
                //this might be null
                curr_joke.setText(current_json.optString("selftext"));
                this.jokes.add(curr_joke);
            }
        } catch (JSONException e) {
            Log.d("pullJokes()", e.toString());
        }
    }


    //just here to make easier to use later.
    public void refreshJokes(){
        this.startConnection();
    }

    public ArrayList<Joke> getJokes(){
        return this.jokes;
    }


}
