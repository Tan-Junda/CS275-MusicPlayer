package jtan5.example.musicplayer;

import androidx.fragment.app.Fragment;

public class MusicPlayerListActivity extends SingleFragmentActivity  {

    @Override
    protected Fragment createFragment() {
        return new PlayerListFragment();
    }
}
