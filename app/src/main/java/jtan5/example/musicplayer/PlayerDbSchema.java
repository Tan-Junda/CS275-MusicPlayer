package jtan5.example.musicplayer;

import android.media.MediaPlayer;

public class PlayerDbSchema {
    public static final class PlayerTable {
        public static final String NAME = "players";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String IS_PLAYING = "isplaying";
            public static final String PLAYER = "uuid2";
        }
    }
}
