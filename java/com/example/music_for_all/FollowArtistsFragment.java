package com.example.music_for_all;

import android.app.AlertDialog;
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
import java.util.List;

public class FollowArtistsFragment extends Fragment {

    private ListView listViewArtists;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private List<Artist> artists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_artists, container, false);

        listViewArtists = view.findViewById(R.id.listViewfollowArtist);
        searchBar = view.findViewById(R.id.searchBar);

        populateListView();

        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artists.get(position);
                showArtistDetailsDialog(artist);
            }
        });

        return view;
    }

    private void populateListView() {
        // List of musicians
        artists = new ArrayList<>();
        artists.add(new Artist("Bob Dylan", "Robert Allen Zimmerman", "May 24, 1941", "American"));
        artists.add(new Artist("Elvis Presley", "Elvis Aaron Presley", "January 8, 1935", "American"));
        artists.add(new Artist("Michael Jackson", "Michael Joseph Jackson", " August 29, 1958", "American"));
        artists.add(new Artist("Prince", "Prince Rogers Nelson", " June 7, 1958", "American"));
        artists.add(new Artist("James Brown", "ames Joseph Brown", " May 3, 1933", "American"));
        artists.add(new Artist("Freddie Mercury", "Freddie Mercury", " September 4, 1981", "British singer of Indian descent"));
        artists.add(new Artist("Jennifer Lopez", "Jennifer Lynn Lopez", " July 24, 1969", "American"));
        artists.add(new Artist("David Bowie", "David Robert Jones", " Sanuary 8, 1947", "American"));
        artists.add(new Artist("Shakira", "Shakira Isabel Mebarak Ripoll", " February 2, 1977", "Colombian-Spanish"));
        artists.add(new Artist("Britney Spears", "Britney Jean Spears", " December 2, 1981", "American"));
        artists.add(new Artist("Christopher Brown", "Christopher Maurice Brown", " May 5, 1989", "American"));
        artists.add(new Artist("Justin  Bieber", "Justin Drew Bieber", " March 1, 1994", "American"));
        artists.add(new Artist("Rihanna", " Robyn Rihanna Fenty", " February 20, 1988", "American"));

        // Create adapter with artist names
        List<String> artistNames = new ArrayList<>();
        for (Artist artist : artists) {
            artistNames.add(artist.getName());
        }
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, artistNames);
        listViewArtists.setAdapter(adapter);
    }

    private void showArtistDetailsDialog(Artist artist) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(artist.getName());
        builder.setMessage("Real Name: " + artist.getRealName() +
                "\nDate of Birth: " + artist.getDateOfBirth() +
                "\nNationality: " + artist.getNationality());
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
