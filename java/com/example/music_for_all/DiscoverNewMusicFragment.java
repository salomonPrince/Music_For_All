package com.example.music_for_all;
import android.media.MediaPlayer;
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

import java.util.Arrays;
import java.util.List;

public class DiscoverNewMusicFragment extends Fragment {

    private ListView listViewSongs;
    private EditText searchBar;
    private ArrayAdapter<String> adapter;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_new_music, container, false);

        listViewSongs = view.findViewById(R.id.listViewSongs);
        searchBar = view.findViewById(R.id.searchBar);
        mediaPlayer = new MediaPlayer();

        populateListView();

        return view;
    }

    private void populateListView() {

        List<String> songTitles = Arrays.asList(
                "Remember Me",
                "One Ticket",
                "Possibility",
                "One love",
                "Woman",
                "Commas",
                "Baridi",
                "Huu Mwaka"
        );

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, songTitles);
        listViewSongs.setAdapter(adapter);

        listViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSong = songTitles.get(position);

                int resourceId = getResources().getIdentifier(selectedSong.toLowerCase().replace(" ", "_"), "raw", requireContext().getPackageName());
                playSong(resourceId);
            }
        });
    }

    private void playSong(int resourceId) {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        try {
            mediaPlayer = MediaPlayer.create(requireContext(), resourceId);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.release();
    }
}
