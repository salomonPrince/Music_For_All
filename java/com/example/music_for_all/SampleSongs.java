package com.example.music_for_all;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleSongs {
    public static Map<String, List<Integer>> getGenreSongsMap() {
        Map<String, List<Integer>> genreSongsMap = new HashMap<>();

        //  sample songs for each genre
        genreSongsMap.put("Country Music", Arrays.asList(R.raw.lock_down, R.raw.one_ticket, R.raw.fix_this));
        genreSongsMap.put("Pop Music", Arrays.asList(R.raw.remember_me, R.raw.blowma, R.raw.elokooyo));
        genreSongsMap.put("Jazz Music", Arrays.asList(R.raw.huu_mwaka,R.raw.essence,R.raw.forever));
        genreSongsMap.put("Hiphop Music", Arrays.asList(R.raw.fix_this,R.raw.unavailable,R.raw.lastlast));
        genreSongsMap.put("RnB Music", Arrays.asList(R.raw.godly,R.raw.komando,R.raw.commas));
        genreSongsMap.put("Rock Music", Arrays.asList(R.raw.lastlast,R.raw.forever,R.raw.berna));
        genreSongsMap.put("Blues Music", Arrays.asList(R.raw.essence,R.raw.one_ticket,R.raw.huu_mwaka));
        genreSongsMap.put("World Traditional Music", Arrays.asList(R.raw.woman,R.raw.happy,R.raw.soso));

        return genreSongsMap;
    }
}
