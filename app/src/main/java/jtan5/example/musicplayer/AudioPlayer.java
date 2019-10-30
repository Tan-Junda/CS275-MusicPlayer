package jtan5.example.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    private MediaPlayer mPlayer;
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
        mPlayer = MediaPlayer.create(c, R.raw.abc);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mPlayer.start();
    }
}
