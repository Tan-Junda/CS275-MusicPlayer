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

public class PlayerPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<MusicPlayer> mMusicPlayers;
    private static final String EXTRA_CRIME_ID = "com.jtan5.example.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, PlayerPagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.player_view_pager);
        mMusicPlayers = MusicPlayerLab.get(this).getMusicPlayers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                MusicPlayer crime = mMusicPlayers.get(position);
                return PlayerItemFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return mMusicPlayers.size();
            }
        });
        for (int i = 0; i < mMusicPlayers.size() ; i++) {
            if (mMusicPlayers.get(i).getmId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}