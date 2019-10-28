package jtan5.example.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PlayerItemFragment extends Fragment {
    private static final int REQUEST_DELETE = 0;
    private static final String DIALOG_DELETE = "DialogDelete";
    private ImageButton mPlayButton;
    private ImageButton mStopButton;
    private ImageButton mPauseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private MusicPlayer mMusic;
    public static final String ARG_MUSIC_ID = "music_id";
    private AudioPlayer mPlayer = new AudioPlayer();
    public static PlayerItemFragment newInstance(UUID musicId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MUSIC_ID, musicId);
        PlayerItemFragment fragment = new PlayerItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID musicId = (UUID) getActivity().getIntent().getSerializableExtra(ARG_MUSIC_ID);
        mMusic = MusicPlayerLab.get(getActivity()).getMusicPlayer(musicId);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container);

        mPlayButton = (ImageButton) v.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.play(getActivity());
            }
        });

        mStopButton = (ImageButton) v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.stop();
            }
        });

        mPauseButton = (ImageButton) v.findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("pause");
            }
        });

        mNextButton = (ImageButton) v.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("next");
            }
        });

        mPreviousButton = (ImageButton) v.findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println("prev");
            }
        });

        return v;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_music:
                FragmentManager manager = getFragmentManager();
                DeleteDialogFragment dialog = new DeleteDialogFragment();
                dialog.setTargetFragment(PlayerItemFragment.this, REQUEST_DELETE);
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
        if (resultCode != Activity.RESULT_OK) return;
        //if (resultCode == REQUEST_DELETE) {

        //}
    }
}
