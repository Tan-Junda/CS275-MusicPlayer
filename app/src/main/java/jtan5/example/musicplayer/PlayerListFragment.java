package jtan5.example.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerListFragment extends Fragment {
    private RecyclerView mPlayerRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;

    /*
        Required interface for hosting activities.
     */
    public interface Callbacks {
        void onPlayerSelected(Player player);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mPlayerRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_item_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        }  else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_music:
                Player player = new Player();
                PlayerLab.get(getActivity()).addPlayer(player);
                updateUI();
                mCallbacks.onPlayerSelected(player);
                return true;

            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        PlayerLab crimeLab = PlayerLab.get(getActivity());
        int playerCount = crimeLab.getPlayers().size();
        String subtitle = getString(R.string.subtitle_format, playerCount);
        if (!mSubtitleVisible) {
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }



    private class PlayerHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTitleTextView;
        private ImageView mImageView;
        private Player mPlayer;
        public PlayerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_player, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_title);
            mImageView = (ImageView) itemView.findViewById(R.id.list_playing);
        }

        public void bind(Player player) {
            mPlayer = player;
            mTitleTextView.setText(mPlayer.getTitle());
            mImageView.setVisibility(mPlayer.isPlaying() ? View.VISIBLE : View.GONE);
        }
        @Override
        public void onClick(View view) {
            mCallbacks.onPlayerSelected(mPlayer);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {
        private List<Player> mPlayers;

        public PlayerAdapter(List<Player> players) {
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            Player player = mPlayers.get(position);
            holder.bind(player);
        }
        @Override
        public int getItemCount() {
            return mPlayers.size();
        }

    }

    public void updateUI() {
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();
        mAdapter = new PlayerAdapter(players);
        mPlayerRecyclerView.setAdapter(mAdapter);
        updateSubtitle();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
