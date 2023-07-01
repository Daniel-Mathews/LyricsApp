package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSongAdapter extends ArrayAdapter<Songs> {
    private List<Songs> songListFull;
    public AutoCompleteSongAdapter(@NonNull Context context,  @NonNull List<Songs> songList) {
        super(context, 0, songList);
        songListFull= new ArrayList<>(songList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return songFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView= LayoutInflater.from(getContext()).inflate(
                    R.layout.customautocompleterow , parent, false
            );
        }
        TextView suggestion= convertView.findViewById(R.id.autoSuggestion);
        Songs song = getItem(position);
        if(song!=null){
            suggestion.setText(Integer.toString(song.getSongNumber())+": "+song.getSongTitle());
        }

        return convertView;
    }

    private Filter songFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence inputTxt) {
            FilterResults results= new FilterResults();
            List<Songs> suggestions= new ArrayList<>();

            if(inputTxt == null || inputTxt.length()==0) {
                suggestions.addAll(songListFull);
            }
            else {
                String inputTxtString= inputTxt.toString().toLowerCase();

                for(Songs songElem: songListFull){
                    if((songElem.getSongLyrics().toLowerCase().replaceAll("\\p{Punct}", "")).contains(inputTxtString.replaceAll("\\p{Punct}", "")) ||
                                Integer.toString(songElem.getSongNumber()).equals(inputTxtString)){
                        suggestions.add(songElem);
                    }
                }

            }
            results.values= suggestions;
            results.count=suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence inputTxt, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return Integer.toString(((Songs) resultValue).getSongNumber());
        }
    };
}
