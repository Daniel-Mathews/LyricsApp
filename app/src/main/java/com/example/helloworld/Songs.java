package com.example.helloworld;

import java.util.*;

/* Class Description:
 This is a class to define a user defined data-type called Songs.
 The class can have various attributes that can be used to describe a song*/

public class Songs extends MainActivity{
    //Class variables.
    private String songLyrics = "";
    private String songFirstLine = "";
    private final int songNumber;
    private String songTitle;
    private ArrayList<String> songTitlesArrayList = new ArrayList<String>();


    //Constructors
    public Songs(int songNumber) {
        this.songNumber = songNumber;
    }

    public Songs(int songNumber, ArrayList<String> songTitlesArrayList) {
        this.songNumber = songNumber;
        this.songTitlesArrayList = songTitlesArrayList;
    }


    //Getters and Setters
    public String getSongLyrics() {
        return songLyrics;
    }

    public void setSongLyricsAndFirstLine() {
        //Index of the song number as a string
        String strSongNumber = Integer.toString(songNumber);

        //Index of the next song number as a string
        String strNextSongNumber = Integer.toString(songNumber + 1);

        //Variable to store index of the end of the first line of the song marked by '\n'
        int firstSlashN;

        //Variable to store index of the start of the next song or where we should stop the current song
        int stopIndex;

        //Index of the start of current song
        int startIndex = songDatabase.indexOf(strSongNumber);

        //While loop to get the index of the first actual lyric (alphabet) of the song without spaces and dots or numbers
        while (true) {
            if (songDatabase.charAt(startIndex) == ' ' ||
                    songDatabase.charAt(startIndex) == '.'  ||
                    Character.isDigit(songDatabase.charAt(startIndex))) {

                startIndex += 1;
            }
            else {
                break;
            }
        }

        /*Since the last song does not have a next song number to signify the end of the current song lyric
        * We will make the last song lyric go to the end of the database*/
        if (songDatabase.indexOf(strNextSongNumber) != -1) {
            stopIndex = songDatabase.indexOf(strNextSongNumber);
        } else {
            stopIndex = songDatabase.length();
        }

        //Setting the song lyrics
        for (int i = startIndex; i < stopIndex; i++) {
            if(songDatabase.charAt(i)==' '&& songDatabase.charAt(i-1)=='\n')
                continue;
            this.songLyrics += songDatabase.charAt(i);
        }

        //Setting the song first line
        firstSlashN = songLyrics.indexOf("\n");
        for (int i = 0; i < firstSlashN; i++) {
            this.songFirstLine += songLyrics.charAt(i);
        }

    }

    public String getSongFirstLine() {
        return songFirstLine;
    }

    public int getSongNumber() {
        return songNumber;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle() {
        this.songTitle = songTitlesArrayList.get(this.songNumber - 1);
    }
}

