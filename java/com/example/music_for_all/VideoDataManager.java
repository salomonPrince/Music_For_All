package com.example.music_for_all;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VideoDataManager {

    private Context context;

    public VideoDataManager(Context context) {
        this.context = context;
    }

    public List<Video> getVideosFromJson() {
        List<Video> videos = new ArrayList<>();
        String jsonString = loadJsonFromRawResource();
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray categories = jsonObject.getJSONArray("categories");
                JSONObject category = categories.getJSONObject(0); // Assuming there's only one category

                JSONArray videosArray = category.getJSONArray("videos");
                for (int i = 0; i < videosArray.length(); i++) {
                    JSONObject videoObject = videosArray.getJSONObject(i);
                    Video video = new Video();
                    video.setTitle(videoObject.getString("title"));
                    video.setDescription(videoObject.getString("description"));
                    video.setAuthor(videoObject.getString("author"));
                    video.setVideoUrl(videoObject.getString("videoUrl"));
                    video.setImageUrl(videoObject.getString("imageUrl"));
                    videos.add(video);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return videos;
    }

    private String loadJsonFromRawResource() {
        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.data);
        String jsonString = null;
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
