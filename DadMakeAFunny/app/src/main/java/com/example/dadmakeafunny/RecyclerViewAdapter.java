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

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> jokes;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> jokes) {
        this.jokes = jokes;
        Log.d(TAG, "RecyclerViewAdapter: jokes size: " + jokes.size());
        this.context = context;
    }


    /*
    * Required overrides
    */

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
        return jokes.size();
    }

    /*
    *   Public exposed functionality
    */
    public void addAndNotify(String added){
        jokes.add(jokes.size(),added);
        notifyItemInserted(jokes.size());
    }

    public void removeAndNotify(){
        // safety check
        if (jokes.size() > 0){
            int index = jokes.size() - 1;
            jokes.remove(index);
            notifyItemRemoved(index);
        }
    }



    /*
    * Getters
    */
    public ArrayList<String> getJokes(){
        return jokes;
    }

    /*
     * Setters
     */


    // 'Holds' a single CardView
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView dadPicture;
        TextView theJoke;
        RelativeLayout relLayout;
        CardView cardView;

            public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.joke_card);
//            dadPicture = itemView.findViewById(R.id.dad_picture);
            theJoke = itemView.findViewById(R.id.the_joke);
            relLayout = itemView.findViewById(R.id.card_relative_layout);
        }
    }
    
}
