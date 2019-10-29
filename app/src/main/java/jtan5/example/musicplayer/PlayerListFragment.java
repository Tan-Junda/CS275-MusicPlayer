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

import java.util.List;

import javax.security.auth.callback.Callback;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerListFragment extends Fragment {
    private RecyclerView mPlayerRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI() {
        PlayerLab musicPlayerLab = PlayerLab.get(getActivity());
        List<Player> musicPlayers = musicPlayerLab.getPlayers();

        mAdapter = new PlayerAdapter(musicPlayers);
        mPlayerRecyclerView.setAdapter(mAdapter);

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
        private List<Player> mMusics;

        public PlayerAdapter(List<Player> musicPlayers) {
            mMusics = musicPlayers;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            Player player = mMusics.get(position);
            holder.bind(player);
        }

        @Override
        public int getItemCount() {
            return mMusics.size();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
