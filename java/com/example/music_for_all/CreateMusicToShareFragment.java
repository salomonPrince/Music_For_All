package com.example.music_for_all;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateMusicToShareFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_music_to_share, container, false);





    /*    Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"https://salomonprince.github.io/salomon.portfolio");
        intent.setType("text/plain");
        startActivity(intent); */

    }
}