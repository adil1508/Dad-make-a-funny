package com.example.dadmakeafunny;


import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerViewAdapter adapter;
    TextToSpeech tl;

    // vars
    private ArrayList<Joke> jokes = new ArrayList<>();
    private JokesReader jokeController = new JokesReader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        initRecyclerView();

        initJokes();

        initButtons();

        tl = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tl.setLanguage(Locale.UK);
                }
            }
        });

    }


    private void initButtons(){
        Button button = findViewById(R.id.button1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a toast menu item

                if (jokes.size() <= 0){
                    initJokes();
                }

                // Add an item to the recycler view
                Joke joke = jokes.remove(0);
                adapter.addAndNotify(joke.toString());
                RecyclerView rc = findViewById(R.id.Recycler_View);
                rc.scrollToPosition(adapter.getItemCount() - 1);

                tl.speak((CharSequence)joke.getTitle(), TextToSpeech.QUEUE_FLUSH, null, "title");
                tl.playSilentUtterance(25, TextToSpeech.QUEUE_ADD, "silence");
                tl.speak(joke.getText(), TextToSpeech.QUEUE_ADD, null, "text");


            }
        });


        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a toast menu item
                // Add an item to the recycler view
                adapter.removeAndNotify();
            }
        });
    }


    //currently sets title of Joke object to jokes array list
    private void initJokes(){
        jokeController.startConnection();
        jokes = jokeController.getJokes();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");

        RecyclerView recyclerView = findViewById(R.id.Recycler_View);

        // set Animator for items (3rd-party)
        recyclerView.setItemAnimator(new LandingAnimator());

        adapter = new RecyclerViewAdapter(this, new ArrayList<String>());

        recyclerView.setAdapter(new SlideInBottomAnimationAdapter(adapter));

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

}