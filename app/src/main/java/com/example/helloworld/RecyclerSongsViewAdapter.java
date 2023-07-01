package com.example.helloworld;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerSongsViewAdapter extends RecyclerView.Adapter<RecyclerSongsViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Songs> songs;

    public RecyclerSongsViewAdapter(Context context, ArrayList<Songs> songs) {
        this.context= context;
        this.songs= songs;
    }

    @NonNull
    @Override
    public RecyclerSongsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.songbuttondisplay, parent, false);

        return new RecyclerSongsViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSongsViewAdapter.MyViewHolder holder, int position) {
        holder.songName.setText(songs.get(position).getSongTitle());
        holder.songNumber.setText(Integer.toString(songs.get(position).getSongNumber()));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView songName;
        TextView songNumber;

        CardView songCard;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            songName= itemView.findViewById(R.id.cardSongName);
            songNumber=itemView.findViewById(R.id.cardSongNumber);
            songCard=itemView.findViewById(R.id.songCard);


            songCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(view.getContext(), LyricsActivity.class);
                    intent.putExtra("songNumber", Integer.parseInt(songNumber.getText().toString()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }
}
