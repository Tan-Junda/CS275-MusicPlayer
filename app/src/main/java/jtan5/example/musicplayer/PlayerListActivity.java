package jtan5.example.musicplayer;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class PlayerListActivity extends SingleFragmentActivity implements
        PlayerListFragment.Callbacks, PlayerFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new PlayerListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public void onPlayerSelected(Player player) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            Intent intent = PlayerPagerActivity.newIntent(this, player.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = PlayerFragment.newInstance(player.getId());
            getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentContainer,
                    newDetail).commit();
        }
    }

    public void onPlayerUpdated(Player player) {
        PlayerListFragment listFragment = (PlayerListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();

    }



}
