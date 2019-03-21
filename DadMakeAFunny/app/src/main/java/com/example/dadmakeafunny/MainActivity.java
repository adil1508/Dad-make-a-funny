package com.example.dadmakeafunny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // vars
    private ArrayList<String> jokes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        initJokes();

        initRecyclerView();

    }

    private void initJokes(){
        jokes.add("This be jokes");
        jokes.add("This be jokes 1");
        jokes.add("This be jokes");
        jokes.add("This be jokes");
        jokes.add("This be jokes");
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");

        RecyclerView recyclerView = findViewById(R.id.Recycler_View);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, jokes);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
