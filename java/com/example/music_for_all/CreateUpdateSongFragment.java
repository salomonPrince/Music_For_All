package com.example.music_for_all;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.music_for_all.R;

public class CreateUpdateSongFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_update_song, container, false);

        // Populates ListView
        populateListView(view);

        return view;
    }

    private void populateListView(View view) {

        // Defines created songs
        String[] createSongs = {
                "Shape Of You\n" +
                        "Ed Sheeran",

                "Despacito\n" +
                        "Luis Fonsi & Daddy Yankee Featuring Justin Bieber",

                "Girls Like You\n" +
                        "Maroon 5 Featuring Cardi B",

                "Perfect\n" +
                        "Ed Sheeran",

                "Sicko Mode\n" +
                        "Travis Scott",

                "God's Plan\n" +
                        "Drake",

                "Happy\n" +
                        "Pharrell Williams",

                "Just The Way You Are\n" +
                        "Bruno Mars",

                "Rockstar\n" +
                        "Post Malone Featuring 21 Savage",

                "See You Again\n" +
                        "Wiz Khalifa Featuring Charlie Puth",

                "That's What I Like\n" +
                        "Bruno Mars",

                "All Of Me\n" +
                        "John Legend",

                "Shake It Off\n" +
                        "Taylor Swift",

                "One Dance\n" +
                        "Drake Featuring WizKid & Kyla",

                "Love Yourself\n" +
                        "Justin Bieber",

                "Love The Way You Lie\n" +
                        "Eminem Featuring Rihanna",

                "Sorry\n" +
                        "Justin Bieber",

                "Hello\n" +
                        "Adele",

                "Work\n" +
                        "Rihanna Featuring Drake",

                "Havana\n" +
                        "Camila Cabello Featuring Young Thug",

                "Bad Guy\n" +
                        "Billie Eilish",

                "What Do You Mean?\n" +
                        "Justin Bieber",

                "Nice For What\n" +
                        "Drake",

                "Come and Get it \n" +
                        "Selena Gomez",

                "Flowers\n" +
                        "Miley Cyrus",

                "Mirrow \n" +
                        "Justin Timberlake",

                "Calm Down\n" +
                        "Rema & Selena Gomez",

                "Die For You\n" +
                        "The Weeknd & Ariana Grande",


                "Under The Influence\n" +
                        "Chris Brown"
        };

        // Finds ListView by id
        ListView listView = view.findViewById(R.id.listViewSongs2);

        // Create an ArrayAdapter to hold Created songs
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                createSongs
        );

        // Set adapter for the ListView
        listView.setAdapter(adapter);


    }
}
