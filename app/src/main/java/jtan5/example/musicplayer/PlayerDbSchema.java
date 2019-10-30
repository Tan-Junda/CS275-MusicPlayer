package jtan5.example.musicplayer;

public class PlayerDbSchema {
    public static final class PlayerTable {
        public static final String NAME = "players";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String IS_PLAYING = "isplaying";
        }
    }
}
