package com.example.music_for_all;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CreateMusicGenreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_create_music_genre, container, false);



        populateListView(view);

        return view;
    }

    private void populateListView(View view) {
        // Define your music genres
        String[] musicGenres = {
                "Country Music",
                "Pop Music",
                "Jazz Music",
                "Hip hop Music",
                "R&B Music",
                "Classical Music",
                "Blues Music",
                "Rock Music",
                "World Traditional Music"
        };



        ListView listView = view.findViewById(R.id.listViewSongs);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                musicGenres
        );



        listView.setAdapter(adapter);



    }
}
