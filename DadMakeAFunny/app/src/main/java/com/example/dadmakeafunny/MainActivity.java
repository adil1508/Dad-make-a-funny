package com.example.dadmakeafunny;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        initButtons();

    }


    private void initButtons(){
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button 1 pressed", Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button 2 pressed", Toast.LENGTH_SHORT).show();
            }
        });
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
