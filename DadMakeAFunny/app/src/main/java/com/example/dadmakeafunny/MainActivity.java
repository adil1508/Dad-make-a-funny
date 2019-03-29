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
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerViewAdapter adapter;

    // vars
    private ArrayList<String> jokes = new ArrayList<>();
    private JokesReader jokeController = new JokesReader();

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
                // Create a toast menu item
                Toast.makeText(MainActivity.this, "Button 1 pressed: ADDED", Toast.LENGTH_SHORT).show();

                // Add an item to the recycler view
                adapter.addAndNotify("Testing Button 1");
                RecyclerView rc = findViewById(R.id.Recycler_View);
                rc.scrollToPosition(adapter.getItemCount() - 1);

            }
        });


        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a toast menu item
                Toast.makeText(MainActivity.this, "Button 2 pressed: DELETED", Toast.LENGTH_SHORT).show();

                // Add an item to the recycler view
                adapter.removeAndNotify();
            }
        });
    }

    // currently doesn't do anything but i feel we might need it for later
    private void initJokes(){
        jokeController.startConnection();
        ArrayList<Joke> jokeList = jokeController.getJokes();
        for(int i =0; i < jokeList.size(); i++){
            jokes.add(jokeList.get(i).getTitle());
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");

        RecyclerView recyclerView = findViewById(R.id.Recycler_View);

        // set Animator for items (3rd-party)
        recyclerView.setItemAnimator(new LandingAnimator());

        adapter = new RecyclerViewAdapter(this, jokes);

        recyclerView.setAdapter(new SlideInBottomAnimationAdapter(adapter));

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

}
