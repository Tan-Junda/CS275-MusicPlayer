package jtan5.example.musicplayer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MusicPlayerActivity extends SingleFragmentActivity {

    public static final String EXTRA_MUSIC_ID = "com.jtan5.example.musicplayer.music_id";
    public static Intent newIntent(Context packageContext, UUID musicId) {
        Intent intent = new Intent(packageContext, MusicPlayerActivity.class);
        intent.putExtra(EXTRA_MUSIC_ID, musicId);
        return intent;
    }
    public Fragment createFragment() {
        return new PlayerItemFragment();
    }
}
