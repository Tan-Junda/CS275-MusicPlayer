package jtan5.example.musicplayer;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class PlayerActivity extends SingleFragmentActivity {

    private static final String EXTRA_PLAYER_ID = "com.jtan5.example.musicplayer.player_id";

    public static Intent newIntent(Context packageContext, UUID playerId) {
        Intent intent = new Intent(packageContext, PlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }

   @Override
    protected Fragment createFragment() {
       UUID playerId = (UUID) getIntent()
               .getSerializableExtra(EXTRA_PLAYER_ID);
       return PlayerFragment.newInstance(playerId);
   }
}
