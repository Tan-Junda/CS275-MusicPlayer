package jtan5.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PlayerPagerActivity extends AppCompatActivity
    implements PlayerFragment.Callbacks {
    private ViewPager mViewPager;
    private List<Player> mPlayers;
    private static final String EXTRA_PLAYER_ID = "com.jtan5.example.musicplayer.player_id";
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, PlayerPagerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_pager);
        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);
        mViewPager = (ViewPager)findViewById(R.id.player_view_pager);
        mPlayers = PlayerLab.get(this).getPlayers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Player crime = mPlayers.get(position);
                return PlayerFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mPlayers.size();
            }
        });
        for (int i =0; i < mPlayers.size(); i++ ) {
            if (mPlayers.get(i).getId().equals(playerId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
    @Override
    public void onPlayerUpdated(Player player) {

    }

}
