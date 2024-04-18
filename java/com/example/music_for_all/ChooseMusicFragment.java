package com.example.music_for_all;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChooseMusicFragment extends Fragment implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private Map<Integer, Integer> audioMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_music, container, false);

        mediaPlayer = new MediaPlayer();
        audioMap = new HashMap<>();
        initializeAudioMap();

        bindTextViews(view);

        return view;
    }

    private void bindTextViews(View view) {
        int[] textViewIds = new int[]{
                R.id.foreverTextView, R.id.helloTextView, R.id.rushTextView,
                R.id.essenceTextView, R.id.waterTextView, R.id.comedownTextView,
                R.id.unavailableTextView, R.id.maydayTextView, R.id.lastlastTextView,
                R.id.sensationalTextView, R.id.godlyTextView, R.id.peruTextView,
                R.id.diorTextView
        };

        for (int id : textViewIds) {
            TextView textView = view.findViewById(id);
            textView.setOnClickListener(this);
        }
    }

    private void initializeAudioMap() {
        audioMap.put(R.id.foreverTextView, R.raw.forever);
        audioMap.put(R.id.helloTextView, R.raw.berna);
        audioMap.put(R.id.rushTextView, R.raw.rush);
        audioMap.put(R.id.essenceTextView, R.raw.essence);
        audioMap.put(R.id.waterTextView, R.raw.miamore);
        audioMap.put(R.id.comedownTextView, R.raw.comedown);
        audioMap.put(R.id.unavailableTextView, R.raw.blowma);
        audioMap.put(R.id.maydayTextView, R.raw.elokooyo);
        audioMap.put(R.id.lastlastTextView, R.raw.lastlast);
        audioMap.put(R.id.sensationalTextView, R.raw.sensational);
        audioMap.put(R.id.godlyTextView, R.raw.godly);
        audioMap.put(R.id.peruTextView, R.raw.peru);
        audioMap.put(R.id.diorTextView, R.raw.bvenue);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    public void onClick(View v) {
        int audioResourceId = audioMap.get(v.getId());
        if (audioResourceId != 0) {
            playAudio(audioResourceId);
        } else {
            Toast.makeText(getContext(), "Audio resource not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio(int audioResourceId) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(getResources().openRawResourceFd(audioResourceId));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException | IllegalStateException e) {
            Toast.makeText(getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
