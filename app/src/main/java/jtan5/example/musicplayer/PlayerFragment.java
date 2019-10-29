package jtan5.example.musicplayer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class PlayerFragment extends Fragment {
    public static final String ARG_PLAYER_ID = "player_id";
    private Player mPlayer;
    private EditText mTitleField;
    private ImageButton mPlayButton;
    private ImageButton mStopButton;
    private boolean isPlaying;
    private AudioPlayer mSounder;

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
        UUID playerId = (UUID)getArguments()
                .getSerializable(ARG_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_player, parent, false);
        mTitleField = (EditText) v.findViewById(R.id.player_title);
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
            }
        });
        mStopButton = (ImageButton) v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSounder.stop();
                isPlaying = false;
                mPlayer.setPlaying(isPlaying);
            }
        });

        return v;
    }
}
