package com.example.music_for_all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateArtistFragment extends Fragment {

    private ListView listViewArtists;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_artist, container, false);


        listViewArtists = view.findViewById(R.id.listViewArtists);
        searchBar = view.findViewById(R.id.searchBar);


        populateListView();


        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    private void populateListView() {
        //  list of musicians
        List<String> musicians = Arrays.asList(
                "Bob Dylan",
                "Elvis Presley",
                "Michael Jackson",
                "Madonna",
                "The Beatles",
                "Elton John",
                "Beyoncé",
                "Frank Sinatra",
                "Queen",
                "Adele",
                "Led Zeppelin",
                "Eminem",
                "Taylor Swift",
                "Prince"
        );


        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, musicians);
        listViewArtists.setAdapter(adapter);
    }
}
