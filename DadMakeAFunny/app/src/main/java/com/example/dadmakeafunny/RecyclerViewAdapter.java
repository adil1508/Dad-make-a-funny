package com.example.dadmakeafunny;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> jokes = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> jokes) {
        this.jokes = jokes;
        Log.d(TAG, "RecyclerViewAdapter: jokes size: " + jokes.size());
        this.context = context;
    }


    //  Inflates the actual item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.joke_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // called each time a new item is added to the RecyclerView container
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG, "onBindViewHolder: called with i: " + i);

        viewHolder.theJoke.setText(jokes.get(i));

        viewHolder.relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Waddup", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: jokes size: " + jokes.size());
        return 5;
    }

    // 'Holds' a single CardView
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView dadPicture;
        TextView theJoke;
        RelativeLayout relLayout;
        CardView cardView;

            public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.joke_card);
            dadPicture = itemView.findViewById(R.id.dad_picture);
            theJoke = itemView.findViewById(R.id.the_joke);
            relLayout = itemView.findViewById(R.id.card_relative_layout);
        }
    }
    
}