package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*Class description:
* This class is used for the search and menu options for songs.*/
public class SearchAndMenuActivity extends AppCompatActivity {

    ArrayList<Songs> songForAutoAdapter;
    ArrayList<Songs> songsForRecycler = new ArrayList<>();

    static ArrayList<Songs> songs= new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.autosearchmenu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        if(songs.size()==0){
            songs= new MainActivity().getSongs();
        }

        /*for(Songs elem: songs){
            System.out.println("==========================================");
            System.out.println("Song Title:"+elem.getSongTitle());
            System.out.println("Song Number:"+elem.getSongNumber());
            System.out.println("Song First Line:"+elem.getSongFirstLine());
            System.out.println("Song Lyrics:"+elem.getSongLyrics());
            System.out.println("==========================================");
        }*/


        View v = findViewById(R.id.homePage);
        onSearchClick(v);


    }


    public void setSongsForRecycler(String input) {
        if (input.length() == 0) {
            songsForRecycler.addAll(songs);
        } else {
            if (Character.isDigit(input.charAt(0)) && input.length() <= 3 && input.matches("\\d+")) {
                for (Songs elem : songs) {
                    if (Integer.toString(elem.getSongNumber()).equalsIgnoreCase(input)) {
                        Intent intent= new Intent(SearchAndMenuActivity.this, LyricsActivity.class);
                        intent.putExtra("songNumber", (elem.getSongNumber()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            } else {
                for (Songs elem : songs) {
                    if ((elem.getSongLyrics().toLowerCase().replaceAll("\\p{Punct}", "")).contains(input.toLowerCase().replaceAll("\\p{Punct}", ""))) {
                        songsForRecycler.add(elem);
                    }
                }
            }
        }

    }

    public void onSearchClick(View view) {
        songForAutoAdapter = new ArrayList<>(songs);
        AutoCompleteTextView autofindsongs = findViewById(R.id.autofindsongs);
        AutoCompleteSongAdapter adapterForAutoComplete = new AutoCompleteSongAdapter(this, songForAutoAdapter);
        autofindsongs.setAdapter(adapterForAutoComplete);
        autofindsongs.setThreshold(1);
        songsForRecycler.clear();

        setSongsForRecycler(autofindsongs.getEditableText().toString());


        RecyclerView recyclerView = findViewById(R.id.recyclersongsView);
        RecyclerSongsViewAdapter adapterForRecyclerView = new RecyclerSongsViewAdapter(this, songsForRecycler);
        recyclerView.setAdapter(adapterForRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}



