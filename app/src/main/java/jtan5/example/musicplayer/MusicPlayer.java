package jtan5.example.musicplayer;

import java.util.UUID;
public class MusicPlayer {

    private UUID mId;
    private String mTitle;
    private boolean isPlaying;
    public MusicPlayer() {
        mId = UUID.randomUUID();
    }
    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public String toString() {return mTitle; }
}
