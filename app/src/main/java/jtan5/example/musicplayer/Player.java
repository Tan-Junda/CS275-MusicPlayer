package jtan5.example.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import java.util.UUID;

public class Player {
    private UUID mId;
    private String mTitle;
    private boolean mIsPlaying;
    private MediaPlayer mPlayer;
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

    public MediaPlayer getMusicPlayer() {
        return mPlayer;
    }
    @Override
    public String toString() {
        return mTitle;
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c) {

        mPlayer = MediaPlayer.create(c, R.raw.bcd);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mPlayer.start();
    }
}
