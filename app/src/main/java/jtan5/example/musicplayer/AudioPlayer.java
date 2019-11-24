package jtan5.example.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.UUID;

public class AudioPlayer {
    private MediaPlayer mPlayer;
    private UUID mId;

    public AudioPlayer(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
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
