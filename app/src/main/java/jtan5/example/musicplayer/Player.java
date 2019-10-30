package jtan5.example.musicplayer;

import java.util.UUID;

public class Player {
    private UUID mId;
    private String mTitle;
    private boolean mIsPlaying;
    private AudioPlayer musicPlayer = new AudioPlayer();

    public Player() {
        mId = UUID.randomUUID();
    }

    public Player(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isPlaying(){
        return mIsPlaying;
    }

    public void setPlaying(boolean playing) {
        mIsPlaying = playing;
    }

    public AudioPlayer getMusicPlayer() {
        return musicPlayer;
    }
    @Override
    public String toString() {
        return mTitle;
    }
}
