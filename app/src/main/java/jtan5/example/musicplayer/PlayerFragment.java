package jtan5.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PlayerFragment extends Fragment {
    public static final String ARG_PLAYER_ID = "player_id";
    private static final String DIALOG_DELETE = "DialogDelete";
    private static final int REQUEST_DELETE = 3;
    private Player mPlayer;
    private EditText mTitleField;
    private ImageButton mPlayButton;
    private ImageButton mStopButton;
    private ImageButton mPauseButton;
    private boolean isPlaying;
    private AudioPlayer mSounder;
    private Callbacks mCallbacks;

    /**
     * Required interface for holding activities.
     */
    public interface Callbacks {
        void onPlayerUpdated(Player player);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public static PlayerFragment newInstance(UUID playerId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER_ID, playerId);

        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID playerId = (UUID)getArguments()
                .getSerializable(ARG_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_player, parent, false);
        mTitleField = (EditText) v.findViewById(R.id.player_title);
        mCallbacks.onPlayerUpdated(mPlayer);
        mTitleField.setText(mPlayer.getTitle());
        mTitleField.addTextChangedListener( new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mPlayer.setTitle(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {

            }

            public void afterTextChanged(Editable c) {

            }
        });
        mPlayButton = (ImageButton) v.findViewById(R.id.play_button);
        mSounder = mPlayer.getMusicPlayer();
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSounder.play(getActivity());
                isPlaying = true;
                mPlayer.setPlaying(isPlaying);
                mCallbacks.onPlayerUpdated(mPlayer);
            }
        });
        mStopButton = (ImageButton) v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSounder.stop();
                isPlaying = false;
                mPlayer.setPlaying(isPlaying);
                mCallbacks.onPlayerUpdated(mPlayer);
            }
        });

        mPauseButton = (ImageButton) v.findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mSounder.pause();
                    isPlaying = false;
                    mPlayer.setPlaying(isPlaying);
                    mCallbacks.onPlayerUpdated(mPlayer);
                }
        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_music:
                FragmentManager manager = getFragmentManager();
                DeleteDialogFragment dialog = new DeleteDialogFragment();
                dialog.setTargetFragment(PlayerFragment.this, REQUEST_DELETE);
                dialog.show(manager,DIALOG_DELETE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_item, menu);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DELETE) {
            PlayerLab.get(getActivity()).deletePlayer(mPlayer);
            getActivity().finish();
        }
    }
}
