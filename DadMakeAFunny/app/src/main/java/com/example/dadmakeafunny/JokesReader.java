package com.example.dadmakeafunny;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        InputStream connection = this.sendGet(url);
        String data = this.readConnection(connection);
        this.pullJokes(data);
    }


    //returns an InputStream from the URL
    private InputStream sendGet(String url){
        InputStream connection = null;
        try {
            connection = new URL(url).openStream();
        }catch (Exception e){
            Log.e("sendGet()", "Connection error" + e.toString());
        }
        return connection;
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
    private String readConnection(InputStream connection){
        String result = "";
        String tmp;
        BufferedReader br = new BufferedReader(new InputStreamReader(connection));
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

    private void pullJokes(String data) {
        return;
    }

    public ArrayList<Joke> refreshJokes(){
        //will get more jokes, after the current ones
    }

    public ArrayList<Joke> getJokes(){
        return this.jokes;
    }

    /*functions left to write
        convert to JSON
        read JSON
        create jokes
        possibly will change desgin of readConnection
     */


}
