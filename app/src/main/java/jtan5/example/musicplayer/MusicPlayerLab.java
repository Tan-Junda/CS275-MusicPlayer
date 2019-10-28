package jtan5.example.musicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicPlayerLab {
    private ArrayList<MusicPlayer> mMusics;
    private static MusicPlayerLab sMusicLab;
    private Context mAppContext;;

    private MusicPlayerLab (Context appContext) {
        mAppContext = appContext;
        mMusics = new ArrayList<MusicPlayer>();
        for (int i=0; i<100; i++) {
            MusicPlayer m = new MusicPlayer();
            m.setmTitle("Music #" + i);
            m.setPlaying(false);
            mMusics.add(m);
        }
    }

    public ArrayList<MusicPlayer> getMusicPlayers() {
        return mMusics;
    }

    public MusicPlayer getMusicPlayer(UUID id) {
        for (MusicPlayer m : mMusics) {
            if (m.getmId().equals(id))
                return m;
        }
        return null;
    }

    public static MusicPlayerLab get(Context m) {
        if (sMusicLab == null) {
            sMusicLab = new MusicPlayerLab(m.getApplicationContext());
        }
        return sMusicLab;
    }

}
