package jtan5.example.musicplayer;

import android.media.MediaPlayer;
import android.provider.MediaStore;

import java.util.UUID;

public class Player {
    private UUID mId;
    private String mTitle;
    private boolean mIsPlaying;
    private AudioPlayer musicPlayer;
    public Player() {
        mId = UUID.randomUUID();
        musicPlayer = new AudioPlayer(mId);
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

    public AudioPlayer getMusicPlayer(UUID playerId) {
        musicPlayer = new AudioPlayer(playerId);
        return musicPlayer;
    }
    @Override
    public String toString() {
        return mTitle;
    }
}
