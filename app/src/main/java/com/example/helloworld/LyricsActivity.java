package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LyricsActivity extends SearchAndMenuActivity{
    int songNumberForLyrics;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songstatsdisplayframework);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            songNumberForLyrics = extras.getInt("songNumber");
        }


        Songs song= songs.get(songNumberForLyrics-1);

        TextView songTitleForLyrics= findViewById(R.id.lyricsPageSongTitle);
        songTitleForLyrics.setText(song.getSongTitle());

        TextView songNumberForLyricsDisplay= findViewById(R.id.lyricsPageSongNumber);
        songNumberForLyricsDisplay.setText(Integer.toString(songNumberForLyrics));

        TextView songLyricsForLyrics= findViewById(R.id.lyricsPageSongLyrics);
        songLyricsForLyrics.setText(song.getSongLyrics());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int newOrientation = newConfig.orientation;
        TextView SongsOfWorship= findViewById(R.id.SongsOfWorship);
        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = SongsOfWorship.getLayoutParams();
        if(newOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Do certain things when the user has switched to landscape.
            int txtHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
            // Changes the height and width to the specified *pixels*
            params.height = height;
            SongsOfWorship.setLayoutParams(params);
            SongsOfWorship.setTextSize(txtHeight);

        }
        else {
            int txtHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            params.height=height;
            SongsOfWorship.setLayoutParams(params);
            SongsOfWorship.setTextSize(txtHeight);

        }


    }
    public void backToHomeClick(View view){
        Intent intent= new Intent(LyricsActivity.this, SearchAndMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
