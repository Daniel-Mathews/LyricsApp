package com.example.helloworld;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Songs> songs= new ArrayList<>();

    static ArrayList<String> songTitlesArrayList;

    static String songDatabase;


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        songs.clear();
        songTitlesArrayList=getSongTitles();
        System.out.println("We bout to start up!");
        getSongDatabase();

        //System.out.println(songDatabase);



        for(Songs elem: songs){
            System.out.println("==========================================");
            System.out.println("Song Title:"+elem.getSongTitle());
            System.out.println("Song Number:"+elem.getSongNumber());
            System.out.println("Song First Line:"+elem.getSongFirstLine());
            System.out.println("Song Lyrics:"+elem.getSongLyrics());
            System.out.println("==========================================");
        }









    }

    public void songObjectCreation(int start, int stop) {
        for(int i=start; i<=stop; i++) {
            Songs obj= new Songs(i, songTitlesArrayList);
            obj.setSongLyricsAndFirstLine();
            obj.setSongTitle();
            songs.add(i-1, obj);
        }
    }

    public void getSongDatabase(){
        songDatabase= (String) this.getText(R.string.songDatabase);
        songObjectCreation(1, 10);
        songDatabase= (String) this.getText(R.string.songDatabase2);
        songObjectCreation(11, 18);
        songDatabase= (String) this.getText(R.string.songDatabase3);
        songObjectCreation(19, 26);
        songDatabase= (String) this.getText(R.string.songDatabase4);
        songObjectCreation(27, 34);
        songDatabase= (String) this.getText(R.string.songDatabase5);
        songObjectCreation(35, 42);
        songDatabase= (String) this.getText(R.string.songDatabase6);
        songObjectCreation(43, 50);
        songDatabase= (String) this.getText(R.string.songDatabase7);
        songObjectCreation(51, 58);
        songDatabase= (String) this.getText(R.string.songDatabase8);
        songObjectCreation(59, 66);
        songDatabase= (String) this.getText(R.string.songDatabase9);
        songObjectCreation(67, 74);
        songDatabase= (String) this.getText(R.string.songDatabase10);
        songObjectCreation(75, 82);
        songDatabase= (String) this.getText(R.string.songDatabase11);
        songObjectCreation(83, 90);
        songDatabase= (String) this.getText(R.string.songDatabase12);
        songObjectCreation(91, 98);
        songDatabase= (String) this.getText(R.string.songDatabase13);
        songObjectCreation(99, 106);
        songDatabase= (String) this.getText(R.string.songDatabase14);
        songObjectCreation(107, 114);
        songDatabase= (String) this.getText(R.string.songDatabase15);
        songObjectCreation(115, 122);
        songDatabase= (String) this.getText(R.string.songDatabase16);
        songObjectCreation(123, 130);
        songDatabase= (String) this.getText(R.string.songDatabase17);
        songObjectCreation(131, 135);


        Intent intent= new Intent(MainActivity.this, SearchAndMenuActivity.class);
        startActivity(intent);
        finish();


    }

    public ArrayList<String> getSongTitles(){
        ArrayList<String> tempTitles =new ArrayList<>();
        Collections.addAll(tempTitles, getResources().getStringArray(R.array.titles));
        return tempTitles;
    }

    public ArrayList<Songs> getSongs(){
        return songs;
    }


}