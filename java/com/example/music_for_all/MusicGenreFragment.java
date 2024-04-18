package com.example.music_for_all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicGenreFragment extends Fragment {

    private Map<String, List<Integer>> genreSongsMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_genre, container, false);

        // Initialize the genre songs map
        genreSongsMap = SampleSongs.getGenreSongsMap();


        populateListView(view);

        return view;
    }

    private void populateListView(View view) {
        String[] musicGenres = getResources().getStringArray(R.array.music_genres);

        ListView listView = view.findViewById(R.id.listViewSongs);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                musicGenres
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = musicGenres[position];
                List<Integer> songIds = genreSongsMap.get(selectedGenre);
                if (songIds != null && !songIds.isEmpty()) {

                    openSongListFragment(selectedGenre, songIds);
                }
            }
        });
    }

    private void openSongListFragment(String genre, List<Integer> songIds) {
        SongListFragment songListFragment = new SongListFragment();
        Bundle args = new Bundle();
        args.putString("genre", genre);
        args.putIntegerArrayList("songIds", new ArrayList<>(songIds));
        songListFragment.setArguments(args);


        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, songListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
