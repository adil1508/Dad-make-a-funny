package com.example.dadmakeafunny;
import android.util.Log;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class JokesReader {
/* class opens connection to reddit and handles all jokes */
    private String url;
    public ArrayList<Joke> jokes;
    private String after;
    private static final String BASE_URL = "https://www.reddit.com/r/dadjokes/.json";
    private String limit = "?limit=20";

    /*constructor*/
    public JokesReader(){
        this.jokes = new ArrayList<>();
        this.after = null;
    }


    /*starts the connection to subreddit and pulls in first 20 jokes*/

    public void startConnection(){
        this.url = createUrl();
        InputStream connection = this.sendGet(url);
        this.jokes = readConnection(connection);
    }


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
        if (this.after == null){
            return this.BASE_URL + this.limit;
        }
        else{
            return this.BASE_URL + "?after=" + this.after + this.limit;
        }
    }

    private ArrayList<Joke> readConnection(InputStream connection){
        //still left to implement
        return null;
    }


    public ArrayList<Joke> refreshJokes(){
        //will get more jokes, after the current ones
    }

    /*functions left to write
        convert to JSON
        read JSON
        create jokes
        possibly will change desgin of readConnection
     */


}
