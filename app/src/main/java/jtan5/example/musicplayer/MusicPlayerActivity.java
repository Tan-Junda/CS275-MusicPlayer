package jtan5.example.musicplayer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import java.util.UUID;

public class MusicPlayerActivity extends SingleFragmentActivity {
    private static final String EXTRA_PLAYER_ID =  "com.jtan5.example.musicplayer.id";

//    public static Intent newIntent(Context packageContext, UUID playerId) {
//        Intent intent = new Intent(packageContext, MusicPlayerActivity.class);
//        intent.putExtra(EXTRA_PLAYER_ID, playerId);
//        return intent;
//    }

    public Fragment createFragment() {
        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);
        return PlayerItemFragment.newInstance(playerId);
    }
}
